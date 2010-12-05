public interface ICarManagement{
  Boolean registerCar(Car car);
  Boolean deleteCar(Car car);
  Boolean updateCar(Car car);
  Car retrieveCar(Object id);
}

public class ICarManagementImpl implements ICarManagement{
  public Boolean registerCar(Car car){
    InitialContext context = new InitialContext();
    CarFacadeRemote remoteInterface = (CarFacadeRemote) context.lookup(CarFacadeRemote());
    return remoteInterface.create(car);
  }

  public Boolean deleteCar(Car car){
    InitialContext context = new InitialContext();
    CarFacadeRemote remoteInterface = (CarFacadeRemote) context.lookup(CarFacadeRemote());
    return remoteInterface.remove(car);
  }

  public Boolean deleteCar(Car car){
    InitialContext context = new InitialContext();
    CarFacadeRemote remoteInterface = (CarFacadeRemote) context.lookup(CarFacadeRemote());
    return remoteInterface.update(car);
  }

  public Car retrieve(Object id){
    InitialContext context = new InitialContext();
    CarFacadeRemote remoteInterface = (CarFacadeRemote) context.lookup(CarFacadeRemote());
    return remoteInterface.find(id);
  }
}

@Remote
public interface CarFacadeRemote{
  Boolean create(Car car);
  Boolean remove(Car car);
  Boolean update(Car car);
  Car find(Object id);
}

@Stateless
public class CarFacadeRemoteImpl implements CarFacadeRemote{
  @PersistenceContext(unitName = "mackenzie-ejbPU")
    private EntityManager em;
  public EntityManager getEntityManager(){
    return em;
  }

  public Boolean create(Car car){
    return getEntityManager().persist(car);
  }

  public remove(Car car){
    return getEntityManager().remove(getEntityManager().merge(car));
  }

  public update(Car car){
    return getEntityManager().merge(car);
  }

  public Car find(Object id){
    return getEntityManager().find(Car.class, id);
  }
}
