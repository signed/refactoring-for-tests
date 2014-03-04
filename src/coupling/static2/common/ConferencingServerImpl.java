package coupling.static2.common;

import java.util.Date;

import common.Chances;
import coupling.common.BookingException;

public class ConferencingServerImpl implements ConferencingServer {

  @Override
  public void bookConference(String topic, Date startDate) throws BookingException {
    if (Chances.isHappyPath()) {
      // NOTE: imagine this connects to the conferencing server and books the conference
      System.out.println("TOPIC: " + topic + " START: " + startDate);
    }
    else {
      throw new BookingException("Failed to book conference: " + topic);
    }
  }
}
