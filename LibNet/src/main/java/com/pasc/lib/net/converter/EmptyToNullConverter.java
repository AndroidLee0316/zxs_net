package com.pasc.lib.net.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by duyuan797 on 17/04/12.
 */

public class EmptyToNullConverter extends Converter.Factory {

  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
          Retrofit retrofit) {
    final Converter<ResponseBody, ?> delegate =
            retrofit.nextResponseBodyConverter(this, type, annotations);
    return new Converter<ResponseBody, Object>() {
      @Override public Object convert(ResponseBody body) throws IOException {
        if (body.contentLength() == 0) return null;
        return delegate.convert(body);
      }
    };
  }
}