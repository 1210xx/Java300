package priv.rj.learning.net.server.demo00;

import priv.rj.learning.net.server.util.CloseUtil;

import java.io.IOException;
import java.net.ServerSocket;



/**
 * ������������������
 * 
 * 1������
 * 2����Ӧ
 * @author Administrator
 *
 */
public class Server {
	private ServerSocket server;
	public static final String CRLF="\r\n";
	public static final String BLANK=" ";
	
	private boolean isShutDown= false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		Server server = new Server();
		server.start();
		
		
	}
	/**
	 * ��������
	 */
	public void start(){		
		start(8899);
	
	}
	/**
	 * ָ���˿ڵ���������
	 */
	public void start(int port){		
		try {
			server = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
	
	}
	/**
	 * ���տͻ���
	 */
	private void receive(){
		try {
			while(!isShutDown){
				new Thread(new Dispatcher(server.accept())).start();
			}
		} catch (IOException e) {
			//e.printStackTrace();
			stop();
		}
		
	}
	
	/**
	 * ֹͣ������
	 */
	public void stop(){
		isShutDown=true;
		CloseUtil.closeSocket(server);
	}
	
	
}
