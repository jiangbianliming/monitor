package pku.edu.cn.test;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import pku.edu.cn.Entity.Edges;
import pku.edu.cn.Entity.Node;
import pku.edu.cn.conn.MetaData;
import pku.edu.cn.json.JsonHelper;

public class TestJsonHelper {
	public static void main(String args[]){
		JsonHelper help = new JsonHelper();
		MetaData meta = new MetaData();
		ResultSet rs= meta.getMetadata();
		ResultSet rs1 =meta.getMetadata(); 
		List<Node> list = help.getNodesList(rs);
//		for(Node node:list){
//			System.out.println(node.getId());
//		}
		List<Edges> listedge = help.getEdgesList(rs1);
//		System.out.println(listedge.size());
		Map map = help.getLegend();
//		System.out.println(map.size());
		System.out.println(help.getDataJson(list, listedge, map).toString());
	}
	public static String getJsonData(){
		JsonHelper help = new JsonHelper();
		MetaData meta = new MetaData();
		ResultSet rs= meta.getMetadata();
		ResultSet rs1 =meta.getMetadata(); 
		List<Node> list = help.getNodesList(rs);
		List<Edges> listedge = help.getEdgesList(rs1);
		Map map = help.getLegend();
		return help.getDataJson(list, listedge, map).toString();

	}
}
