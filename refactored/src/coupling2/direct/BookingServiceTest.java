package coupling2.direct;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;

import coupling.BookingResult;
import coupling2.ConferencingServer;
import coupling2.MeetingCalendar;

public final class BookingServiceTest {

  // mocked collaborators
  private final ConferencingServer server = mock(ConferencingServer.class);
  private final MeetingCalendar calendar = mock(MeetingCalendar.class);

  // object under test
  private final BookingService service = new BookingService(server, calendar);

  @Test
  public void testConferenceBooking_HappyPath() throws Exception {
    // arrange
    Date startDate = new Date();
    when(calendar.nextPossibleDate()).thenReturn(startDate);

    // act
    BookingResult result = service.bookConference("Test Conference");

    // assert
    assertTrue(result.isSuccess());
    verify(server).bookConference("Test Conference", startDate);
  }

}
