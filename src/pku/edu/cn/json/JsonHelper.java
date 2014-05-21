package pku.edu.cn.json;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import pku.edu.cn.Entity.Edges;
import pku.edu.cn.Entity.Node;
import pku.edu.cn.Entity.NodesScale;
import pku.edu.cn.Entity.NodesType;
import pku.edu.cn.conn.MetaData;
import net.sf.json.JSONObject;

public class JsonHelper {
	public List getNodesList(ResultSet reslut){
		List list = new ArrayList();
		try {
			MetaData meta = new MetaData();
			List<String> iplist = meta.getDataCenterIpList();
//			InitZnodeInfo info = new InitZnodeInfo();
//			String mainCenter = info.getLeader(iplist);
			while(reslut.next()){
				Node node = new Node();
				node.setId(reslut.getString("ipAddr"));
				if(reslut.getString("nodeKind").equals("0")){
					node.setName("用户系统");
					node.setShape("sphere");
					node.setColor("rgb(0,255,0)");
					node.setSize("1");
				}
				else if(reslut.getString("nodeKind").equals("1")){
					node.setName("数据访问结点");
					node.setShape("square");
					node.setColor("rgb(255,0,0)");
					node.setSize("1");
				}
				else{
					node.setName("数据中心");
					node.setShape("star");
					node.setColor("rgb(255,255,0)");
					if(reslut.getString("ipAddr").equals("")){
						node.setSize("3");
					}
					else{
						node.setSize("1");
					}
//					node.setSize("1");
				}
				node.setGroup(reslut.getString("nodeKind"));
				list.add(node);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
	}
	public List getEdgesList(ResultSet reslut){
		List list = new ArrayList<>();
		try {
			List<Edges> centerList = new ArrayList<Edges>();
			while(reslut.next()){
				if(reslut.getString("upIpAddr")!=null){
					Edges edge = new Edges();
					edge.setId1(reslut.getString("ipAddr"));
					edge.setId2(reslut.getString("upIpAddr"));
					edge.setColor("rgb(0,255,0)");
					edge.setType("line");
					list.add(edge);
				}
				else{
					Edges edge = new Edges();
					edge.setId1(reslut.getString("ipAddr"));
					edge.setColor("rgb(0,255,0)");
					edge.setType("line");
					centerList.add(edge);
				}	
			}
			int centerNodeSize = centerList.size();
			for(int j = 0;j<centerNodeSize;j++){
				int i = j;
				while(i<centerNodeSize-1){
					Edges edge = new Edges();
					edge.setId1(centerList.get(j).getId1());
					edge.setId2(centerList.get(i+1).getId1());
					edge.setColor(centerList.get(j).getColor());
					edge.setType(centerList.get(j).getType());
					list.add(edge);
					i++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public Map<String,Map> getLegend(){
		Map map = new HashMap<String,List>();
		List NodesList = new ArrayList<>();
		NodesType nodesType1 = new NodesType();
		nodesType1.setShape("star");
		nodesType1.setColor("rgb(255,255,0)");
		nodesType1.setText("数据中心");
		nodesType1.setId("nodesLegendId1");
		NodesType nodesType2 = new NodesType();
		nodesType2.setShape("square");
		nodesType2.setColor("rgb(255,0,0)");
		nodesType2.setText("数据访问节点");
		nodesType2.setId("nodesLegendId2");
//		NodesType nodesType3 = new NodesType();
//		nodesType3.setShape("star");
//		nodesType3.setColor("rgb(255,255,0)");
//		nodesType3.setText("ABCD");
//		nodesType3.setId("nodesLegendId3");
		NodesType nodesType4 = new NodesType();
		nodesType4.setShape("sphere");
		nodesType4.setColor("rgb(0,255,0)");
		nodesType4.setText("用户系统");
		nodesType4.setId("nodesLegendId4");
		NodesList.add(nodesType1);
		NodesList.add(nodesType2);
//		NodesList.add(nodesType3);
		NodesList.add(nodesType4);
		map.put("nodes", NodesList);
		NodesScale nodesScale = new NodesScale();
		nodesScale.setX(-500);
		nodesScale.setY(-700);
		Map nodesMap = new HashMap();
		nodesMap.put("nodes", nodesScale);
		map.put("pos", nodesMap);
		return map;
	}
	public static void main(String args[]){
		Node node = new Node();
		node.setColor("sds");
		node.setGroup("sss");
		node.setId("aaa");
		node.setName("xxx");
		node.setShape("zzz");
		Node node1 = new Node();
		node1.setColor("sds1");
		node1.setGroup("sss");
		node1.setId("aaa");
		node1.setName("xxx");
		node1.setShape("zzz");
		NodesScale scale = new NodesScale();
		scale.setX(-500);
		scale.setY(-700);
		List list = new ArrayList();
		list.add(node);
		list.add(node1);
		
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		Map<String,Object> map3 = new HashMap<>();
		
		map1.put("nodes", list);
		map3.put("nodes", scale);
		map1.put("pos", map3);
		map.put("nodes", list);
		map.put("eadgs",list);
		map.put("legend",map1);
	
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);

	}
	public JSONObject getDataJson(List nodeList,List edgesList,Map legend){
		Map map = new HashMap<>();
		map.put("nodes", nodeList);
		map.put("edges", edgesList);
		map.put("legend", legend);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject;
	}

	public String getAddNodeJson(Node node,Object edge){
		Map map = new HashMap<>();
		map.put("addNode", node);
		map.put("addEdge", edge);
		JSONObject jsonObject = JSONObject.fromObject(map);
		return jsonObject.toString();	
	}

}
