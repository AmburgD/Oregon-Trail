/**
 * @author Dax Amburgy, Teagan Hendricks
 * @version 3.0 fixed day 1 issue and added case in case someone tried to break the program
 */
public class Date {
			
	String wordMonth = " ";
	
		/**
	 * @param month
	 * @param day
	 * @param year
	 */
	public Date(int month, int day, int year) {
		super();
		this.month = month;
		this.day = day;
		this.year = year;
	}
		private int month;
		private int day;
		private int year;
		/**
		 * @return the month in string form
		 */
		public String setWordMonth(String wordMonth) {
			this.wordMonth = wordMonth;
			return wordMonth;
		}
		/**
		 * @return the date in string form
		 */
		public String getStringDate() {
			switch(this.month) {
			case 1 :
				wordMonth = "January"; break;
			case 2 :
				wordMonth = "Febuary"; break;
			case 3 :
				wordMonth = "March"; break;
			case 4 :
				wordMonth = "April"; break;
			case 5 :
				wordMonth = "May"; break;
			case 6 :
				wordMonth = "June"; break;
			case 7 :
				wordMonth = "July"; break;
			case 8 :
				wordMonth = "August"; break;
			case 9 :
				wordMonth = "September"; break;
			case 10 :
				wordMonth = "October"; break;
			case 11 :
				wordMonth = "November"; break;
			case 12 :
				wordMonth = "December"; break;
			case 13 :
				wordMonth = "Julianuary"; break;
			
				
			}
			return wordMonth + " " + Integer.toString(day) + " " + Integer.toString(year);
		}
		/**
		 * @return the month
		 */
		public int getMonth() {
			return month;
		}
		/**
		 * @return the day
		 */
		public int getDay() {
			return day;
		}
		/**
		 * @return the year
		 */
		public int getYear() {
			return year;
		}
		
		public String incrementDate() {
			String newDate = "";
			
			System.out.println("month " + this.month);
			System.out.println("day " + this.day);
			System.out.println("day change");
			if(this.month <= 12) {
				if(this.month == 0 ) {
					System.out.println("BZZRT: DAY 0 ERROR");
					this.month++;
				}
				else if (this.month == 1) {
					wordMonth = "January";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
						if(this.day == 31) {
							
						}
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "Febuary";
					}
				}
				else if (this.month == 2) {
					wordMonth = "February";
					if(this.day < 28) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "March";
					}
				}
				else if (this.month == 3) {
					wordMonth = "March";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "April";
					}
				}
				else if (this.month == 4) {
					wordMonth = "April";
					if(this.day < 30) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "May";
					}
				}
				else if (this.month == 5) {
					wordMonth = "May";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "June";
					}
				}
				else if (this.month == 6) {
					wordMonth = "June";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "July";
					}
				}
				else if (this.month == 7) {
					wordMonth = "July";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "August";
					}
				}
				else if (this.month == 8) {
					wordMonth = "August";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "September";
					}
				}
				else if (this.month == 9) {
					wordMonth = "September";
					if(this.day < 30) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "October";
					}
				}
				else if (this.month == 10) {
					wordMonth = "October";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
						
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "November";
					}
				}
				else if (this.month == 11) {
					wordMonth = "November";
					if(this.day < 30) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month++;
						wordMonth = "December";
					}
				}
				else if (this.month == 12) {
					wordMonth = "December";
					if(this.day < 31) {
						if(this.day == 0 ) {
							System.out.println("BZZRT: DAY 0 ERROR");
						}
						this.day++;
					}
					else {
						this.day = 1;
						this.month = 1;
						this.year++;
						wordMonth = "January";
						
						
					}
				}
				
			}
			else if (month >= 13){
				//named after King Julian XIII
				wordMonth = "Julianuary";
				if(this.day < 31) {
					if(this.day == 0 ) {
						System.out.println("BZZRT: DAY 0 ERROR");
					}
					this.day++;
				}
				else {
					this.day = 1;
					this.month = 1;
					this.year++;
					wordMonth = "January";
					
				}
			}
			else {
				wordMonth = "BZZRT: WORD MONTH ERROR";
			}
			System.out.println("month " + this.month);
			System.out.println("day " + this.day);
			newDate = day + " " + wordMonth + " " + year;
			return newDate;
		}
}

