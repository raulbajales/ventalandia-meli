package com.ventalandia.service;

import java.util.List;

/**
 * 
 * @author matias
 *
 */
public class SearchResult {

    private List<NewsFeed> result;
    private boolean moreResults = false;
    
    public void setResult(List<NewsFeed> list) {
        result = list;
    }

    public List<NewsFeed> getResult() {
        return result;
    }

    public boolean moreResults() {
        return moreResults;
    }

    public void setMoreResults(boolean moreResults) {
        this.moreResults = moreResults;
    }

}
