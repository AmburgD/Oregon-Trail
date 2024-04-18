/**
 * @author Dax Amburgy
 * @version 1.0
 * {@summary A class that generates the NPC text when you click on talk to people in the menu of a town that you can stop at}
 */
public class NPCtext {
			
		//the NPC's name
		private String npc;
		
		//what the character has to say
		private String statement;
		
		
		/**
		 * @param npc
		 * @param townNumber
		 * @param statement
		 */
		public NPCtext(String npc, String statement) {
			super();
			this.npc = npc;
			this.statement = statement;
		}
		
		
		public NPCtext() {
			// TODO Auto-generated constructor stub
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
