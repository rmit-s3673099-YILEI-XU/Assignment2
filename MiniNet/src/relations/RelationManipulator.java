package relations;
/**
 * This is the interface to be implemented add and remove relationship feature
 * @author YILEI XU
 *
 */
public interface RelationManipulator {
/**
 * This method is for add relationship
 * @throws Exception
 */
	public void add() throws Exception;
	
/**
 * This method is for remove relationship
 */
	public void remove();
}
