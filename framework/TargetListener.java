/**
 * 
 */
package framework;


/**
 * Holds target functions
 * @author Jonas
 *
 */
public interface TargetListener {

	/**
	 * A specific target has been hit
	 * @param target The target that has been hit
	 */
	void targetHit(GameObject target);
	
	/**
	 * A specific target was destroyed
	 * @param target The target that was destroyed
	 */
	void targetDestroyed(GameObject target);
	
}
