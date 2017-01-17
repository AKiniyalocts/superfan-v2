package com.akiniyalocts.superfan.network.converters;

import com.akiniyalocts.superfan.annotations.Envelope;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class ConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Envelope.class)){
                Type envelopeType = TypeToken.getParameterized(ProductType.class, type).getType();

                final Converter<ResponseBody, ProductType> delegate = retrofit.nextResponseBodyConverter(this, envelopeType, annotations);

                return new Converter<ResponseBody, Object>() {

                    @Override
                    public Object convert(ResponseBody value) throws IOException {
                        ProductType productType = delegate.convert(value);

                        return productType.products;
                    }
                };
            }
        }


        return super.responseBodyConverter(type, annotations, retrofit);

    }
}