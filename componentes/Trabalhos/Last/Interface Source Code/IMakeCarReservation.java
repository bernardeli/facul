public interface IMakeCarReservation{
  Boolean createReservation(Car car, Date start, Date end, Client client);
  List<Car> getAvailableCars(RentalChain rentalChain, Customer customer);
  List<RentalChain> getRentalChains(String city);
  List<String> getCities(); 
}



public class IMakeCarImpl implements IMakeCarReservation{
  public Boolean createReservation(Car car, Date start, Date end, Client client){
    InitialContext context = new InitialContext();
    ReservationFacadeRemote  remoteInterface = (ReservationFacadeRemote) context.lookup(ReservationFacadeRemote.class.getName());
    return remoteInterface.create(new Reservation(car, start, end, client);
  }

  public List<Car> getAvailableCars(RentalChain rentalChain, Customer customer){
    InitialContext context = new InitialContext();
    CarFacadeRemote remoteInterface = (CarFacadeRemote) context.lookup(CarFacadeRemote.class.getName());
    return remoteInterface.findCarByCustomer(customer);
  }

  public List<RentalChain> getRentalChains(String city){
    InitialContext context = new InitialContext();
    RentalChainFacadeRemote remoteInterface = (RentalChainFacadeRemote) context.lookup(RentalChainFacadeRemote.class.getName());
    return remoteInterface.findRentalChainByCity(city);
  }

  public List<String> getCities(){
    InitialContext context = new InitialContext();
    CityFacadeRemote remoteInterface = (CityFacadeRemote) context.lookup(CityFacadeRemote.class.getName());
    return remoteInterface.findAll();
  }
}

@Remote
public interface ReservationFacadeRemote{
  Boolean create(Reservation reservation);
}

@Remote
public interface CarFacadeRemote{
  List<Car> findCarByCustomer(Customer customer);
}

@Remote
public interface RentalChainFacadeRemote{
  List<RentalChain> findRentalChainByCity(String city);
}

@Remote
public interface CityFacadeRemote{
  List<String> findAll();
}

@Stateless
public class ReservationFacadeRemoteImpl implements ReservationFacadeRemote{
  @PersistenceContext(unitName = "mackenzie-ejbPU")
    private EntityManager em;

  public EntityManager getEntityManager(){
    return em;
  }

  public Boolean create(Reservation reservation){
    getEntityManager().persist(reservation);
  }
}

@Stateless
public class CarFacadeRemoteImpl implements CarFacadeRemote{
  @PersistenceContext(unitName = "mackenzie-ejbPU")
    private EntityManager em;

  public EntityManager getEntityManager(){
    return em;
  }

  public List<Car> findCarByCustomer(Customer customer){
    return getEntityManager().createNamedQuery("searchCarByCustomer").setParameter("customerId", customer.getId()).getResultList();
  }
}

@Stateless
public class RentalChainFacadeRemoteImpl implements RentalChainFacadeRemote{
  @PersistenceContext(unitName = "mackenzie-ejbPU")
    private EntityManager em;

  public EntityManager getEntityManager(){
    return em;
  }

  public List<RentalChain> findRentalChainByCity(String city){
    return getEntityManager().createNamedQuery("searchRentalChainByCity").setParameter("city", city).getResultList();
  }
}

@Stateless
public class CityFacadeRemoteImpl implements CityFacadeRemote{
  @PersistenceContext(unitName = "mackenzie-ejbPU")
    private EntityManager em;

  public EntityManager getEntityManager(){
    return em;
  }

  public List<String> findAll(){
    return getEntityManager().createNamedQuery("retrieveAllCities").getResultList();
  }
}
