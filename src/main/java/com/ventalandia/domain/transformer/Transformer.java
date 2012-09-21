package com.ventalandia.domain.transformer;

/**
 * 
 * @author matias
 * 
 * @param <I> type parameter to set the Input (source) to be transformed.
 * @param <O> type parameter to set the Output (result) of transformation.
 */
public interface Transformer<I, O> {

    /**
     * The result is a new instance.
     * 
     * @param in
     * @return
     */
    O transform(I in);

}
