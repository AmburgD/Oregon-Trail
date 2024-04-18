/**
 * @author Dax Amburgy
 * @version 1.0
 * {@summary A class that generates the NPC text when you click on talk to people in the menu of a town that you can stop at}
 */
public class NPCtext {
			
		//the NPC's name
		private String npc;
		
		//an integer representation of the town
		private int townNumber;
		
		//an integer representing where on the trail they can be located
		private int segment;
		
		//what the character has to say
		private String statement;
		
		
		/**
		 * @param npc
		 * @param townNumber
		 * @param statement
		 * @return 
		 */
		public NPCtext(String npc, int townNumber, int segment, String statement) {
			super();
			this.npc = npc;
			this.townNumber = townNumber;
			this.segment = segment;
			this.statement = statement;
		}
		
		
		/**
		 * @return the npc
		 */
		public String getNpc() {
			return npc;
		}

		/**
		 * @param npc the npc to set
		 */
		public void setNpc(String npc) {
			this.npc = npc;
		}

		/**
		 * @return the townNumber
		 */
		public int getTownNumber() {
			return townNumber;
		}

		/**
		 * @param townNumber the townNumber to set
		 */
		public void setTownNumber(int townNumber) {
			this.townNumber = townNumber;
		}

		/**
		 * @return the segment
		 */
		public int getSegment() {
			return segment;
		}


		/**
		 * @param segment the segment to set
		 */
		public void setSegment(int segment) {
			this.segment = segment;
		}

		/**
		 * @return the statement
		 */
		public String getStatement() {
			return statement;
		}

		/**
		 * @param statement the statement to set
		 */
		public void setStatement(String statement) {
			this.statement = statement;
		}

		

		
		
}
