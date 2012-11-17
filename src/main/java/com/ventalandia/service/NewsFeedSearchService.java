package com.ventalandia.service;

import java.util.Collections;
import java.util.List;

import com.google.inject.Inject;

/**
 * All functionality related with search must be here.
 * 
 * @author matias
 * 
 */
public class NewsFeedSearchService {

    private static final String[] EMPTY_KEYWORDS = new String[] {};

    public static final int SEARCH_PAGE_SIZE = 10;

    @SuppressWarnings("unchecked")
    private static final List<NewsFeed> EMPTY_LIST = Collections.EMPTY_LIST;

    @Inject
    private NewsFeedRepository newsFeedRepository;

    /**
     * @see NewsFeedSearchService#search(String, int)
     */
    public List<NewsFeed> search(String keywords) {
        return this.search(keywords, 0);
    }

    /**
     * Perform a simple search, this is all keywords that match with the content
     * (and related content) of a {@link NewsFeed} , and returns a subset
     * corresponding to the specified page. The search is not case sensitive.
     * 
     * @param keywords (i.e.: "color of the table").
     * @param page (i.e.: 2).
     * @return A subset of a list of {@link NewsFeed} which title match with
     * keywords (all keywords).
     */
    public List<NewsFeed> search(String keywords, int page) {
        if (keywords == null || keywords.isEmpty()) {
            return EMPTY_LIST;
        }

        String[] cleanKeywords = this.clean(keywords);

        if (cleanKeywords.length == 0) {
            return EMPTY_LIST;
        }

        if (page > 0) {
            List<NewsFeed> result = this.newsFeedRepository.findByContent(cleanKeywords, SEARCH_PAGE_SIZE * (page - 1), (SEARCH_PAGE_SIZE * page) + 1);
            return result.subList(0, SEARCH_PAGE_SIZE - 1);
        }

        return this.newsFeedRepository.findByContent(cleanKeywords);
    }

    public SearchResult getSearchResult(String keywords, int page) {
        if (keywords == null || keywords.isEmpty()) {
            SearchResult result = new SearchResult();
            result.setResult(EMPTY_LIST);
            return result;
        }

        String[] cleanKeywords = this.clean(keywords);

        if (cleanKeywords.length == 0) {
            SearchResult result = new SearchResult();
            result.setResult(EMPTY_LIST);
            return result;
        }

        return search(cleanKeywords, page);
    }

    private SearchResult search(String[] cleanKeywords, int page) {
        SearchResult result = new SearchResult();

        if (page > 0) {
            List<NewsFeed> newsFeedList = this.newsFeedRepository.findByContent(cleanKeywords, SEARCH_PAGE_SIZE * (page - 1), (SEARCH_PAGE_SIZE * (page + 1)));
            if (newsFeedList.size() > SEARCH_PAGE_SIZE) {
                result.setMoreResults(true);
                result.setResult(newsFeedList.subList(0, SEARCH_PAGE_SIZE));
            }
            else {
                result.setResult(newsFeedList);
            }
        }
        else {
            result.setResult(this.newsFeedRepository.findByContent(cleanKeywords));
        }

        return result;
    }

    public SearchResult searchAll(int page) {
        return this.search(EMPTY_KEYWORDS, page);
    }

    private String[] clean(String keywords) {
        String result = keywords.toLowerCase();
        String[] results = result.split("[^a-z&&[^0-9]]");

        // TODO old stuff - check 
        // if (results.length > 4) {
        // // TODO: fix this... I don't remember how to get a sub-array.
        // return new String[] { results[0], results[1], results[2], results[3]
        // };
        // }
        // else {
        // return results;
        // }

        return results;
    }
}
