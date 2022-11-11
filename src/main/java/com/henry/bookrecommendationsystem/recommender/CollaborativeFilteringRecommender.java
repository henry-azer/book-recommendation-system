package com.henry.bookrecommendationsystem.recommender;

import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.repository.BookRepository;
import com.henry.bookrecommendationsystem.repository.UserBookRatingRepository;
import com.henry.bookrecommendationsystem.repository.UserRepository;
import com.henry.bookrecommendationsystem.utils.ValueComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Henry Azer
 * @since 9/11/2022
 */
@Slf4j
@Component
public class CollaborativeFilteringRecommender {

    private static final int NUM_NEIGHBOURHOODS = 10;
    private static final int NUM_RECOMMENDATIONS = 20;
    private static final int MIN_VALUE_RECOMMENDATION = 4;

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserBookRatingRepository userBookRatingRepository;

    private Map<Long, Map<Long, Integer>> ratings;
    private Map<Long, Double> averageRating;

    public CollaborativeFilteringRecommender(UserRepository userRepository, BookRepository bookRepository, UserBookRatingRepository userBookRatingRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userBookRatingRepository = userBookRatingRepository;
        ratings = new HashMap<>();
        averageRating = new HashMap<>();
    }

    public List<Book> recommendedBooks(Long userId) {
        log.info("CollaborativeFilteringRecommender: recommendedBooks() called - user id: " + userId);
        Map<Long, Double> averageRating = new HashMap<>();
        Map<Long, Map<Long, Integer>> myRatesMap = new TreeMap<>();
        Map<Long, Map<Long, Integer>> userWithRatesMap = new TreeMap<>();

        userRepository.findAll().forEach(userItem -> {
            Long userID = userItem.getId();
            Map<Long, Integer> userRatings = new HashMap<>();

            userBookRatingRepository.findAllByUserIdAndMarkedAsDeletedFalse(userID).forEach(userBookRating -> {
                        if (userBookRating.getUser().getId().compareTo(userID) == 0) {
                            userRatings.put(userBookRating.getBook().getId(), userBookRating.getRate());
                        }
                    }
            );

            if (userId.compareTo(userID) == 0) {
                myRatesMap.put(userID, userRatings);
            } else {
                userWithRatesMap.put(userID, userRatings);
                setRatings(userWithRatesMap);
                averageRating.put(userID, 0.0);
                for (Map.Entry<Long, Integer> longIntegerEntry : userRatings.entrySet()) {
                    if (ratings.containsKey(userID)) {
                        ratings.get(userID).put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                        averageRating.put(userID, averageRating.get(userID) + (double) longIntegerEntry.getValue());
                    } else {
                        Map<Long, Integer> bookRating = new HashMap<>();
                        bookRating.put(longIntegerEntry.getKey(), longIntegerEntry.getValue());
                        ratings.put(userID, bookRating);
                        averageRating.put(userID, (double) longIntegerEntry.getValue());
                    }
                }
            }
        });

        for (Map.Entry<Long, Double> longDoubleEntry : averageRating.entrySet()) {
            if (ratings.containsKey(longDoubleEntry.getKey())) {
                longDoubleEntry.setValue(longDoubleEntry.getValue() / (double) ratings.get(longDoubleEntry.getKey()).size());
            }
        }

        setAverageRating(averageRating);
        Map<Long, String> books = new HashMap<>();
        bookRepository.findAll().forEach(book -> books.put(book.getId(), book.getName()));

        Map<Long, Double> neighbourhoods = getNeighbourhoods(myRatesMap.get(userId));
        Map<Long, Double> recommendations = getRecommendations(myRatesMap.get(userId), neighbourhoods, books);

        ValueComparator valueComparator = new ValueComparator(recommendations);
        Map<Long, Double> sortedRecommendations = new TreeMap<>(valueComparator);
        sortedRecommendations.putAll(recommendations);

        Iterator<Map.Entry<Long, Double>> sortedREntries = sortedRecommendations.entrySet().iterator();
        List<Book> recommendedBooks = new ArrayList<>();

        int i = 0;
        while (sortedREntries.hasNext() && i < NUM_RECOMMENDATIONS) {
            Map.Entry<Long, Double> entry = sortedREntries.next();
            if (entry.getValue() >= MIN_VALUE_RECOMMENDATION) {
                Optional<Book> optionalBook = bookRepository.findById(entry.getKey());
                optionalBook.ifPresent(recommendedBooks::add);
                i++;
            }
        }
        log.info("CollaborativeFilteringRecommender: recommendedBooks() ended - user id: " + userId);
        return recommendedBooks;
    }

    private void setRatings(Map<Long, Map<Long, Integer>> ratings) {
        this.ratings = ratings;
    }

    private void setAverageRating(Map<Long, Double> averageRating) {
        this.averageRating = averageRating;
    }

    /**
     * Get the k-nearest neighbourhoods using Pearson:
     * sim(i,j) = numerator / (sqrt(userDenominator^2) * sqrt(otherUserDenominator^2))
     * numerator = sum((r(u,i) - r(u)) * (r(v,i) - r(v)))
     * userDenominator = sum(r(u,i) - r(i))
     * otherUserDenominator = sum(r(v,i) - r(v))
     * r(u,i): rating of the book i by the user u
     * r(u): average rating of the user u
     *
     * @param userRatings ratings of the user
     * @return nearest neighbourhoods
     */
    private Map<Long, Double> getNeighbourhoods(Map<Long, Integer> userRatings) {
        Map<Long, Double> neighbourhoods = new HashMap<>();
        ValueComparator valueComparator = new ValueComparator(neighbourhoods);
        Map<Long, Double> sortedNeighbourhoods = new TreeMap<>(valueComparator);

        double userAverage = getAverage(userRatings);
        for (long user : ratings.keySet()) {
            ArrayList<Long> matches = new ArrayList<>();
            for (long bookId : userRatings.keySet()) {
                if (ratings.get(user).containsKey(bookId)) {
                    matches.add(bookId);
                }
            }
            double matchRate;
            if (matches.size() > 0) {
                double numerator = 0, userDenominator = 0, otherUserDenominator = 0;
                for (long bookId : matches) {
                    double u = userRatings.get(bookId) - userAverage;
                    double v = ratings.get(user).get(bookId) - averageRating.get(user);

                    numerator += u * v;
                    userDenominator += u * u;
                    otherUserDenominator += v * v;
                }
                if (userDenominator == 0 || otherUserDenominator == 0) {
                    matchRate = 0;
                } else {
                    matchRate = numerator / (Math.sqrt(userDenominator) * Math.sqrt(otherUserDenominator));
                }
            } else {
                matchRate = 0;
            }

            neighbourhoods.put(user, matchRate);
        }
        sortedNeighbourhoods.putAll(neighbourhoods);
        Map<Long, Double> output = new TreeMap<>();

        Iterator<Map.Entry<Long, Double>> entries = sortedNeighbourhoods.entrySet().iterator();
        int i = 0;
        while (entries.hasNext() && i < NUM_NEIGHBOURHOODS) {
            Map.Entry<Long, Double> entry = entries.next();
            if (entry.getValue() > 0) {
                output.put(entry.getKey(), entry.getValue());
                i++;
            }
        }
        return output;
    }

    /**
     * Get predictions of each book by a user giving some ratings and its neighbourhood:
     * r(u,i) = r(u) + sum(sim(u,v) * (r(v,i) - r(v))) / sum(abs(sim(u,v)))
     * sim(u,v): similarity between u and v users
     * r(u,i): rating of the book i by the user u
     * r(u): average rating of the user u
     *
     * @param userRatings    ratings of the user
     * @param neighbourhoods nearest neighbourhoods
     * @param books          books in the database
     * @return predictions for each book
     */
    private Map<Long, Double> getRecommendations(Map<Long, Integer> userRatings, Map<Long, Double> neighbourhoods, Map<Long, String> books) {
        Map<Long, Double> predictedRatings = new HashMap<>();

        // r(u)
        double userAverage = getAverage(userRatings);

        for (Long bookId : books.keySet()) {
            if (!userRatings.containsKey(bookId)) {

                // sum(sim(u,v) * (r(v,i) - r(v)))
                double numerator = 0;

                // sum(abs(sim(u,v)))
                double denominator = 0;

                for (Long neighbourhood : neighbourhoods.keySet()) {
                    if (ratings.get(neighbourhood).containsKey(bookId)) {
                        double matchRate = neighbourhoods.get(neighbourhood);
                        numerator +=
                                matchRate * (ratings.get(neighbourhood).get(bookId) - averageRating.get(neighbourhood));
                        denominator += Math.abs(matchRate);
                    }
                }

                double predictedRating = 0;
                if (denominator > 0) {
                    predictedRating = userAverage + numerator / denominator;
                    if (predictedRating > 5) {
                        predictedRating = 5;
                    }
                }
                predictedRatings.put(bookId, predictedRating);
            }
        }
        return predictedRatings;
    }

    /**
     * Get average of the ratings of a user
     *
     * @param userRatings ratings of a user
     * @return average or the ratings of a user
     */
    private double getAverage(Map<Long, Integer> userRatings) {
        double userAverage = 0;
        for (Map.Entry<Long, Integer> longIntegerEntry : userRatings.entrySet()) {
            userAverage += (int) ((Map.Entry<?, ?>) longIntegerEntry).getValue();
        }
        return userAverage / userRatings.size();
    }
}
