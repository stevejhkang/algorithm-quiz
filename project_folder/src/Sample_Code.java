import java.net.*;
import java.io.*;
public class Sample_Code {

	// User and Game Server Information
	static final String NICKNAME = "junhukang";
	static final String HOST = "127.0.0.1";
	static final int PORT = 1447; // Do not modify
	
	// predefined variables(Do not modify these values)
	static final int TABLE_WIDTH = 254;
	static final int TABLE_HEIGHT = 127;
	static final int NUMBER_OF_BALLS = 5;
	static final int[][] HOLES = {{0, 0}, {127, 0}, {254, 0}, {0, 127}, {127, 127}, {254, 127}};
	
	public static void main(String[] args) {
		
		Socket socket = null;
		String recv_data = null;
		byte[] bytes = new byte[1024];
		int[][] balls = new int[NUMBER_OF_BALLS][2];

		try {
			socket = new Socket();
			System.out.println("Trying Connect: " + HOST + ":" + PORT);
			socket.connect(new InetSocketAddress(HOST, PORT));
			System.out.println("Connected: " + HOST + ":" + PORT);
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			String send_data = "9901/" + NICKNAME;
			bytes = send_data.getBytes("UTF-8");
			os.write(bytes);
			os.flush();
			System.out.println("Ready to play.");
			int cnt = 0;
			while (socket != null) {
				
				bytes = new byte[1024];
				
				int readByteCount = is.read(bytes);
				recv_data = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("Data Received: " + recv_data);
				
				String[] split_data = recv_data.split("/");
				if (split_data[0].equals("9909")) break;
				
				int idx = 0;
				try {
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						for (int j = 0; j < 2; j++) {
							balls[i][j] = Integer.parseInt(split_data[idx++]);
						}
					}
				}
				catch (Exception e) {
					bytes = new byte[1024];
					balls = new int[NUMBER_OF_BALLS][2];
					bytes = "9902/9902".getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Received Data has been currupted, Resend Requested.");
					continue;
				}
				
//				double angle=0;
//				double power=0;
//				if(cnt==0) {
//					 angle = 104.7; 
//					 power = 100;
//					
//				}
//				else if(cnt==1) {
//					 angle = 260;
//					 power=60;
//				}
//				cnt++;
				
				


//				double angle = 0;
//				            double power = 0;
//				            if(cnt == 0) {
//				               angle = 92.2;
//				               power = 100;
//				            }
//				            else if(cnt == 1) {
//				               angle = 82;
//				               power = 50;
//				            }
//				            else if(cnt == 2) {
//				               angle = 182;
//				               power = 40;
//				            }
//				            cnt++;
				double power=0;
				double angle = 0;
				 for (int i = 1; i < balls.length; i++) {
		               if(balls[i][0] >= 0 && balls[i][1] >= 0 && balls[i][0] <= TABLE_WIDTH &&  balls[i][1] <= TABLE_HEIGHT) {
		                  int bx = balls[0][0];
		                  int by = balls[0][1];
		                  
		                  int targetx = balls[i][0];
		                  int targety = balls[i][1];
		                  
		                  int length_x = targetx - bx;
		                  int length_y = targety - by;
		                  
		                  power = length_x*length_x + length_y*length_y;
		                  
//		                  power=100;
		                  power = Math.sqrt(power)/3;
//		                  if(power>100) {
//		                	  power = Math.sqrt(power)/2;
//		                  }
//		                  power += 10;
		                  
		                  System.out.println(length_x + " " + length_y);
		                  angle = Math.atan2(length_x, length_y);
		                  angle *= 180.0 / Math.PI;
		                  
		                  if(angle % 90 == 0) {
		                     angle += 0.5;
		                  }
		                  break;
		               }
		            }
				
//	               if(cnt == 0) {
//	                  angle = 91.8;
//	                  power = 90;
//	               }
//	               else if(cnt == 1) {
//	                  angle = 287.312;
//	                  power = 90;
//	               }
//	               else if(cnt == 2) {
//	                  angle = 43;
//	                  power = 50;
//	               }
//	               else if(cnt == 3) {
//	                  angle = 173.5;
//	                  power = 90;
//	               }
//	               cnt++;
//	               angle = 92;
//	               power = 100;
//				System.out.println(angle);
				
				////////////////////////////////////////////////////////////////////
				// 주석을 지우고, 이 곳에 주어진 정보를 바탕으로 게임 로직을 구현하여 자동으로 플레이할 수 있도록 구현합니다.
    			// 필요한 결괏값은 방향(angle)과 세기(power)로 두 변수의 값이 이 부분에서 결정되도록 만들어야 합니다.
				//////////////////////////////////////////////////////////////////
				
				
				
				String merged_data = angle + "/" + power;
				bytes = merged_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Data Sent: " + merged_data);
			}
			os.close();
			is.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
