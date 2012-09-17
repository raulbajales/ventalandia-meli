package com.ventalandia.domain.transformer;

/**
 * 
 * @author matias
 * 
 * @param <I>
 * @param <O>
 */
public interface Transformer<I, O> {

    O transform(I in);

}
