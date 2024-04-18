/**
 * @author Dax Amburgy
 *
 */
public class Location {

			private String name;
			private int segPos;
			private int position;
			private int type;	// 1 = settlement, 2 = river crossing, 3 = vista, 4 = trail fork, 5 = end of trail
			/**
			 * @return the name
			 */
			public String getName() {
				
				return name;
			}
			
			
			/**
			 * @return the segPos
			 */
			public int getSegPos() {
				return segPos;
			}


			/**
			 * @return the position
			 */
			public int getPosition() {
				return position;
			}
			/**
			 * @return the type
			 */
			public int getType() {
				return type;
			}
			/**
			 * @param name
			 * @param position
			 * @param type
			 */
			public Location(String name, int segPos, int position, int type) {
				super();
				this.name = name;
				this.segPos = segPos;
				this.position = position;
				this.type = type;
			}
			
}
