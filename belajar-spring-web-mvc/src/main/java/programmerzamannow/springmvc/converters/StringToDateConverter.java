package programmerzamannow.springmvc.converters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {

  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Override
  public Date convert(String source) {
    try {
      return simpleDateFormat.parse(source);
    } catch (ParseException e) {
      log.warn("Error convert value {} to date", source, e);
      return null;
    }
  }
}
