package coupling2.context;

class Usage {

  public static void main(String[] args) throws Exception {
    BigFatContext context = new BigFatContextImpl();
    BookingService service = new BookingService(context);
    service.bookConference("Big Trouble in Little China");
  }
}
