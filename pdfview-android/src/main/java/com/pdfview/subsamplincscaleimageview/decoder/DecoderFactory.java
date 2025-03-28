package com.pdfview.subsamplincscaleimageview.decoder;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;

/**
 * Interface for {@link ImageDecoder} and {@link ImageRegionDecoder} factories.
 * @param <T> the clazz of decoder that will be produced.
 */
public interface DecoderFactory<T> {

    /**
     * Produce a new instance of a decoder with type {@link T}.
     * @return a new instance of your decoder.
     * @throws IllegalAccessException if the factory clazz cannot be instantiated.
     * @throws InstantiationException if the factory clazz cannot be instantiated.
     * @throws NoSuchMethodException if the factory clazz cannot be instantiated.
     * @throws InvocationTargetException if the factory clazz cannot be instantiated.
     */
    @NonNull T make() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

}
