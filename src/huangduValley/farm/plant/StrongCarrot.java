package huangduValley.farm.plant;

/**
 * @ClassName:StrongPotato
 * @Description:used for abstract factory, planted by fertile land
 * @author CuiYanshen
 */

public class StrongCarrot extends Carrot {
	public StrongCarrot(String landType) {
		super(landType);
		System.out.println("StrongCarrot is planted.");
	}
}
