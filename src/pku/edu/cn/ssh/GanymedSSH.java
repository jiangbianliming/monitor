//package pku.edu.cn.ssh;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.regex.Pattern;
//
//import ch.ethz.ssh2.ChannelCondition;
//import ch.ethz.ssh2.Connection;
//import ch.ethz.ssh2.Session;
//import ch.ethz.ssh2.StreamGobbler;
//
//public class GanymedSSH {
//	private Connection conn;
//	private Session sess;
//	private final int TIME_OUT = 1000 * 5 * 60;
//	public void openConn(String host,int port,String username,String password) throws IOException{
//		conn = new Connection(host,port);
//		conn.connect();
//		Boolean isAuthenticated = conn.authenticateWithPassword(username, password);
//		if(!isAuthenticated){
//			throw new IOException("连接服务器失败："+host);
//		}
//	}
//	public void closeConn(){
//		if(conn!=null){
//			conn.close();
//		}
//	}
//	public void closeSess(){
//		if(sess!=null){
//			sess.close();
//		}
//	}
//	public int runShell(String cmd){
//		int ret=1;
//		try {
//			sess = conn.openSession();
//			sess.execCommand(cmd);
//			sess.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
//			ret = sess.getExitStatus().intValue();
//			closeSess();
//			if(ret!=0){
//				throw new IOException("Shell 命令执行失败"+cmd);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ret;
//	}
//	public InputStream excuteShell(String cmd){
//		InputStream stdout = null; 
//		try {
//			sess = conn.openSession();
//			sess.execCommand(cmd);
//			sess.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
//			stdout = new StreamGobbler(sess.getStdout());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		sess.close();
//		return stdout;
//	}
//	public String getPid(String cmd,String sys_path){
//		String pid="";
//		try {
//			sess = conn.openSession();
//			sess.execCommand(cmd);
//			InputStream stdout = new StreamGobbler(sess.getStdout());
//			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
//			String line;
//			while((line=br.readLine())!=null){
//				if(line.contains(sys_path)){
//					String lineAttr[] = line.split(" ");
//					for(String attr:lineAttr){
//						if(!"".equals(attr.trim())){
//							if(isNumeric(attr.trim())){
//								pid=attr.trim();
//								System.out.println(pid);
//								break;		
//							}
//						}
//					}
//				}
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		sess.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);
//		int ret = sess.getExitStatus().intValue();
//		if(ret!=0){
//			try {
//				throw new IOException("shell 命令失败"+cmd);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//		}	
//		return pid;	
//	}
//	private boolean isNumeric(String trim) {
//		// TODO Auto-generated method stub
//		Pattern pattern = Pattern.compile("[0-9]*");
//        return pattern.matcher(trim).matches();
//	}
//	public static void main(String[] args){
//		GanymedSSH ssh = new GanymedSSH();
//		try {
//			ssh.openConn("10.7.100.82", 22, "root", "system2012");
//			String cmd = "ps -ef|grep gitlab";
//			ssh.getPid(cmd, "/var/log/gitlab/sidekiq");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
