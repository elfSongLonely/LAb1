package application;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

class Trie {
	static int maxn = 1500, sig = 26;
	int[][] ch = new int[maxn][sig];
	int[] index = new int[maxn];
	int[] head = new int[maxn];
	String[] ss = new String[100];
	private int tot, root, cnt;

	public int getcnt() {
		return cnt;
	}

	private int newnode() {
		for (int i = 0; i < sig; i++)
			ch[tot][i] = -1;
		index[tot] = -1;
		return tot++;
	}

	public void init() {
		tot = 0;
		cnt = 0;
		root = newnode();
	}

	public void insert(String s) {
		int len = s.length(), p = root;
		for (int i = 0; i < len; i++) {
			if (ch[p][s.charAt(i) - 'a'] == -1)
				ch[p][s.charAt(i) - 'a'] = newnode();
			p = ch[p][s.charAt(i) - 'a'];
		}
		if (index[p] == -1) {
			index[p] = ++cnt;
			ss[cnt] = s;
		}
	}

	public int search(String s) {
		int len = s.length(), p = root;
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) < 'a' || s.charAt(i) > 'z')
				return -1;
			if (ch[p][s.charAt(i) - 'a'] == -1)	
				return -1;
			p = ch[p][s.charAt(i) - 'a'];
		}
		return index[p];
	}

	public String getString(int x) {
		if (x < 1 || x > cnt)
			return null;
		return ss[x];
	}
}

class Graph {
	Edge[] Edgearray;
	int head[];
	int toedgecnt[];
	int tot = 0;
	private int vertexNum = 0;
	final int maxn = 500;

	public void setvertexNum(int _vertexNum) {
		this.vertexNum = _vertexNum;
	}

	public int getvertexNum() {
		return this.vertexNum;
	}

	public Graph() {
		init();
	}

	public void init() {
		head = new int[maxn];
		toedgecnt = new int[maxn];
		Edgearray = new Edge[maxn];
		tot = 0;
		for (int i = 0; i < maxn; i++)
			Edgearray[i] = new Edge();
		for (int i = 0; i < head.length; i++)
			head[i] = -1;
		for (int i = 0; i < toedgecnt.length; i++)
			toedgecnt[i] = 0;
	}

	public void add(int u, int v) {
		for (int i = head[u]; i != -1; i = Edgearray[i].getnext()) {
			if (Edgearray[i].getnumber() == v) {
				Edgearray[i].setcost();
				return;
			}
		}
		tot++;
		Edgearray[tot].setnumber(v);
		Edgearray[tot].setcost();
		Edgearray[tot].setnext(head[u]);
		head[u] = tot;
		toedgecnt[u]++;
	}

//	private void getBridgeDFS(int u, int parent, int dep, int w2, Vector<Integer> ans, boolean[] vis) {
//		if (dep == 2) {
//			if (u == w2)
//				ans.addElement(Integer.valueOf(parent));
//			return;
//		}
//		for (int i = head[u]; i != -1; i = Edgearray[i].getnext()) {
//			int v = Edgearray[i].getnumber();
//			if (vis[i] == false) {
//				vis[i] = true;
//				getBridgeDFS(v, u, dep + 1, w2, ans, vis);
//			}
//		}
//	}
//
//	public Vector<Integer> getBridge(int w1, int w2) {
//		if (w1 < 1 || w1 > getvertexNum())
//			return null;
//		boolean[] vis = new boolean[maxn];
//		Vector<Integer> ret = new Vector<Integer>();
//		getBridgeDFS(w1, -1, 0, w2, ret, vis);
//		return ret;
//	}
	private void getBridgeDFS(int u, int parent, int dep, int w2, Vector<Integer> ans) {
		if (dep == 2) {
			if (u == w2)
				ans.addElement(Integer.valueOf(parent));
			return;
		}
		for (int i = head[u]; i != -1; i = Edgearray[i].getnext()) {
			int v = Edgearray[i].getnumber();
			getBridgeDFS(v, u, dep + 1, w2, ans);
		}
	}

	public Vector<Integer> getBridge(int w1, int w2) {
		if (w1 < 1 || w1 > getvertexNum())
			return null;
		Vector<Integer> ret = new Vector<Integer>();
		for (int i = 0; i < head[w1]; i++) {
			if (Edgearray[i].number == w2 && Edgearray[i].cost == 1)
				return ret;
		}
		getBridgeDFS(w1, -1, 0, w2, ret);
		return ret;
	}

	public Vector<Integer> insertBridge(Vector<Integer> orig) {
		Vector<Integer> ret = new Vector<Integer>();
		int size = orig.size();
		for (int i = 0; i < size - 1; i++) {
			Vector<Integer> v = getBridge(orig.elementAt(i), orig.elementAt(i + 1));
			if (v != null && v.size() > 0)
				ret.add(v.elementAt(0));
			else
				ret.add(-1);
		}
		ret.add(-1);
		return ret;
	}

	class dijkstraNode {
		int u, dis;

		public dijkstraNode(int _u, int _dis) {
			u = _u;
			dis = _dis;
		}
	}

	private int dijkstra(int u, int v) {
		int dis[] = new int[maxn];
		for (int i = 0; i < dis.length; i++)
			dis[i] = Integer.MAX_VALUE;
		dis[u] = 0;
		boolean vis[] = new boolean[maxn];
		Comparator<dijkstraNode> cmp = new Comparator<dijkstraNode>() {
			public int compare(dijkstraNode n1, dijkstraNode n2) {
				return n2.dis - n1.dis;
			}
		};
		PriorityQueue<dijkstraNode> q = new PriorityQueue<>(cmp);
		q.add(new dijkstraNode(u, 0));
		while (!q.isEmpty()) {
			dijkstraNode tmp = q.poll();
			int uu = tmp.u;
			if (vis[uu])
				continue;
			vis[uu] = true;
			for (int i = head[uu]; i != -1; i = Edgearray[i].getnext()) {
				int vv = Edgearray[i].getnumber();
				if (dis[vv] > dis[uu] + Edgearray[i].getcost()) {
					dis[vv] = dis[uu] + Edgearray[i].getcost();
					q.add(new dijkstraNode(vv, dis[vv]));
				}
			}
		}
		return dis[v];
	}

	private void getPathDFS(int u, int v, int dep, int cost, int dis, Vector<int[]> vec, boolean[] vis, int path[]) {
		if (cost >= dis) {
			if (cost == dis && u == v) {
				path[dep] = v;
				int[] apath = new int[dep + 1];
				for (int i = 0; i < apath.length; i++)
					apath[i] = path[i];
				vec.addElement(apath);
			}
			return;
		}
		for (int i = head[u]; i != -1; i = Edgearray[i].getnext()) {
			int to = Edgearray[i].number;
			if (vis[to])
				continue;
			vis[to] = true;
			path[dep] = u;
			getPathDFS(to, v, dep + 1, cost + Edgearray[i].cost, dis, vec, vis, path);
			vis[to] = false;
		}
	}

	public Vector<int[]> getPath(int u, int v) {
		Vector<int[]> ret = new Vector<int[]>();
		int dis = dijkstra(u, v);
		boolean vis[] = new boolean[maxn];
		int[] path = new int[maxn];
		vis[u] = true;
		getPathDFS(u, v, 0, 0, dis, ret, vis, path);
		return ret;
	}


	public Vector<Integer> randomWalk() {
		Vector<Integer> vector = new Vector<Integer>();
		boolean visit[] = new boolean[maxn];
		for (int i = 0; i < maxn; i++)
			visit[i] = false;
		Random rand = new Random();
		int u = rand.nextInt(vertexNum) + 1;
		vector.add(u);
		while (true) {
			if (toedgecnt[u] == 0)
				break;
			int randnumber = rand.nextInt(toedgecnt[u]), count = 0;
			boolean finish = false;
			for (int i = head[u]; i != -1; i = Edgearray[i].getnext()) {
				if (count++ == randnumber) {
					u = Edgearray[i].getnumber();
					vector.add(u);
					if (visit[i])
						finish = true;
					visit[i] = true;
					break;
				}
			}
			if (finish)
				break;
		}
		return vector;
	}

	Vector<int[]> getEdges() {
		Vector<int[]> ret = new Vector<int[]>();
		for (int i = 1; i <= vertexNum; i++) {
			for (int j = head[i]; j != -1; j = Edgearray[j].next) {
				int[] tmp = new int[3];
				tmp[0] = i;
				tmp[1] = Edgearray[j].number;
				tmp[2] = Edgearray[i].cost;
				ret.add(tmp);
			}
		}
		return ret;
	}
	
	class Edge {
		private int cost;
		private int number;
		private int next;

		Edge() {
			this.next = -1;
			this.cost = 0;
		}

		void setcost() {
			this.cost++;
		}

		int getcost() {
			return this.cost;
		}

		void setnumber(int number) {
			this.number = number;
		}

		int getnumber() {
			return this.number;
		}

		void setnext(int next) {
			this.next = next;
		}

		int getnext() {
			return this.next;
		}
	}
}

public class Main extends Application {
	private File file;
	Trie trie = new Trie();
	Graph graph = new Graph();
	final int maxn = 500;
	static int randomCount=0;
	String randomStr=new String();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) 
    {
		Main a = new Main();
    	a.Init();
        primaryStage.setTitle("实验一");
        Button opentxt = new Button();
        Button showgraph=new Button();
        Button findbirdgewords=new Button();
        Button createnewtxt = new Button();
        Button shortestpath=new Button();
        Button randomwalk=new Button();
        opentxt.setText("读入文本生成有向图");
        showgraph.setText("展示有向图");
        findbirdgewords.setText("寻找桥接词");
        createnewtxt.setText("生成新文本");
        shortestpath.setText("计算最短路径");
        randomwalk.setText("随机游走");
        opentxt.setTranslateY(-180);
        showgraph.setTranslateY(-110);
        findbirdgewords.setTranslateY(-40);
        createnewtxt.setTranslateY(30);
        shortestpath.setTranslateY(100);
        randomwalk.setTranslateY(170);
        opentxt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage txt=new Stage(); 
               openText(txt,a);
            }
        });
        showgraph.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage DirectedGraph=new Stage(); 
               showDirectedGraph(DirectedGraph,a);
            }
        });
        findbirdgewords.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage birdgewords=new Stage(); 
               findDirdgeWords(birdgewords,a);
            }
        });
        createnewtxt.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage newTxt=new Stage(); 
               createNewTxt(newTxt,a);
            }
        });
        shortestpath.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage shortestPath=new Stage(); 
               searchShortestPath(shortestPath,a);
            }
        });
        randomwalk.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
               Stage randomWalk=new Stage(); 
               RandomWalk(randomWalk,a);
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(opentxt);
        root.getChildren().add(showgraph);
        root.getChildren().add(findbirdgewords);
        root.getChildren().add(createnewtxt);
        root.getChildren().add(shortestpath);
        root.getChildren().add(randomwalk);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    public void openText(Stage primaryStage,Main a)
    {
    	 Label flag = new Label();
    	 primaryStage.setTitle("读入文本生成有向图");
    	 primaryStage.setWidth(500);
    	 primaryStage.setHeight(500);
         Button graph = new Button();
         graph.setText("选择文件");
         graph.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 FileChooser fileChooser = new FileChooser();
                 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                 fileChooser.getExtensionFilters().add(extFilter);
                 file = fileChooser.showOpenDialog(primaryStage);
                 if(file!=null)
                 {
                	 a.build(file);
                	 flag.setText("打开成功");
                	 flag.setTextFill(Color.GREEN);
                	 flag.setTranslateY(20);
                 }
                 else
                 {
                	 file=new File("D:\\1.txt");
                	 a.build(file);
                	 flag.setText("文件为空，使用默认值D:\\\\1.txt");
                	 flag.setTextFill(Color.RED);
                	 flag.setTranslateY(20);
                 }
             }
         });
         StackPane root = new StackPane();
         root.getChildren().add(graph);
         root.getChildren().add(flag);
         primaryStage.setScene(new Scene(root, 600, 600));
         primaryStage.show();   
    }
    public void showDirectedGraph(Stage primaryStage,Main a)
    {
    	primaryStage.setTitle("显示有向图");
    	primaryStage.setTitle("随机游走");
    	GridPane root =new GridPane();
    
     	GraphViz gV = new GraphViz();
    	gV.addln(gV.start_graph());
    	Vector<String> sv = a.getEdges();
		for (int i = 0; i < sv.size(); i++)
			gV.addln(sv.elementAt(i));
    	gV.addln(gV.end_graph());
    	File file = new File("bin\\1.jpg");
    	gV.writeGraphToFile(gV.getGraph(gV.getDotSource(), "jpg"), file);
    	
    	
    	Button button=new Button("展示有向图");
    	ImageView imageView = new ImageView();
        imageView.setVisible(true);
        imageView.setFitWidth(900);
        imageView.setFitHeight(900);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                imageView.setImage(new Image("1.jpg"));
            }
        });
        root.add(button, 0, 0);
        root.add(imageView,1,0,3,3);
        primaryStage.setScene(new Scene(root, 1100, 1100));
        primaryStage.show();
    }
    public void findDirdgeWords(Stage primaryStage,Main a)
    {
    	primaryStage.setTitle("查找桥接词");
    	GridPane root =new GridPane();
    	Label label1=new Label("Word1:");
    	Label label2=new Label("Word2:");
        TextField textFieldA = new TextField();
        TextField textFieldB = new TextField();
        TextField textFieldC = new TextField();
        Button button=new Button("查找");
        root.add(label1, 0, 0);
        root.add(label2, 0, 1);
        root.add(textFieldA, 1, 0);
        root.add(textFieldB, 1, 1);
        root.add(button, 1, 2);
        root.add(textFieldC, 0, 3,2,1);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {    	
                textFieldC.setText(a.queryBridgeWords(textFieldA.getText(),textFieldB.getText()));
            }
        });
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    public void createNewTxt(Stage primaryStage,Main a)
    {
    	primaryStage.setTitle("生成新文本");
    	GridPane root =new GridPane();
    	Label label1=new Label("输入新文本:");
    	Label label2=new Label("生成新文本:");
        TextField textFieldA = new TextField();
        TextField textFieldB = new TextField();
        Button button=new Button("生成");
        root.add(label1, 0, 0);
        root.add(textFieldA, 1, 0);
        root.add(button, 1, 1);
        root.add(label2, 0, 2);
        root.add(textFieldB, 1, 2);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
                textFieldB.setText(a.generateNewText(textFieldA.getText()));
            }
        });
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    public void searchShortestPath(Stage primaryStage,Main a)
    {
    	primaryStage.setTitle("寻找最短路径");
    	GridPane root =new GridPane();
    	Label label1=new Label("Word1:");
    	Label label2=new Label("Word2:");
        TextField textFieldA = new TextField();
        TextField textFieldB = new TextField();
        TextArea textArea = new TextArea();
        textArea.setMaxSize(300,300);
        Button button=new Button("查找");
        root.add(label1, 0, 0);
        root.add(label2, 0, 1);
        root.add(textFieldA, 1, 0);
        root.add(textFieldB, 1, 1);
        root.add(button, 1, 2);
        root.add(textArea, 0, 3,4,4);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.setText(a.calShortestPath(textFieldA.getText(), textFieldB.getText()));
            }
        });
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    public void RandomWalk(Stage primaryStage,Main a) {
    	primaryStage.setTitle("随机游走");
    	GridPane root =new GridPane();
    	Button button=new Button("下一节点");
    	TextField text=new TextField();
    	text.setPrefWidth(600);
    	//text.setPrefHeight(400);
    	String randRet = a.randomWalk();
    	String[] ss = randRet.split("\\s+");
    	randomCount = 0;
    	randomStr="";
    	button.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
            	 if (randomCount < ss.length) {
            		 randomStr=randomStr+ss[randomCount]+" ";
	                 text.setText(randomStr);
	                 randomCount++;
            	 }
             }
         });
        root.add(button, 0, 0);
        root.add(text, 0, 1,4,1);
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    Vector<String> input(File filename, boolean tolower) {
		InputStream in = null;
		int tempbyte;
		int word_number = 0, word_new = 0;
		String[] s = new String[maxn];
		try {
			in = new FileInputStream(filename);
			while ((tempbyte = in.read()) != -1) {
				if ((tempbyte >= 'a' && tempbyte <= 'z') || (tempbyte >= 'A' && tempbyte <= 'Z')) {
					if (word_new == 0)
						word_number++;
					if (s[word_number] != null)
						s[word_number] += (char) tempbyte;
					else
						s[word_number] = String.valueOf((char) tempbyte);
					word_new = 1;
				} else {
					word_new = 0;
					if(tolower==true)
						if (s[word_number] != null)
							s[word_number] = s[word_number].toLowerCase();
				}
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Vector<String> ret = new Vector<String>();
		for (int i = 1; i <= word_number; i++)
			ret.add(s[i]);
		return ret;
	}

	void Init() {
		graph.init();
		trie.init();
	}

	void build(File inputText) {
		Vector<String> s = input(inputText, true);
		int size = s.size();
		int[] inx = new int[size];
		for (int i = 0; i < size; i++) {
			this.trie.insert(s.elementAt(i));
			inx[i] = this.trie.search(s.elementAt(i));
		}
		this.graph.setvertexNum(this.trie.getcnt());
		for (int i = 0; i < size - 1; i++)
			this.graph.add(inx[i], inx[i + 1]);
	}

	String queryBridgeWords(String word1, String word2) {
		int w1 = trie.search(word1), w2 = trie.search(word2);
		if (w1 == -1 || w2 == -1)
			return "No \"" + word1 + "\" or \"" + word2 + "\" in the graph!";
		Vector<Integer> v = graph.getBridge(w1, w2);
		String ret = new String();
		if (v.size() == 0)
			ret = "No bridge words from \"" + word1 + "\"  to \"" + word2 + "\"!";
		else if (v.size() == 1)
			ret = "The bridge word from \"" + word1 + "\" to \"" + word2 + "\" is: " + trie.getString(v.elementAt(0));
		else {
			ret = "The bridge words from \"" + word1 + "\" to \"" + word2 + "\" are:";
			for (int i = 0; i < v.size(); i++) {
				if (i == v.size() - 1)
					ret += " and " + trie.getString(v.elementAt(i)) + ".";
				else
					ret += " " + trie.getString(v.elementAt(i)) + ",";
			}
		}
		return ret;
	}

	String generateNewText(String inputText) {
		String[] StrArr = inputText.split("\\s+");
		Vector<String> v = new Vector<String>();
		for (int i = 0; i < StrArr.length; i++)
			v.add(StrArr[i]);
		Vector<Integer> inx = new Vector<Integer>();
		StringBuilder[] sarr = new StringBuilder[v.size()];
		for (int i = 0; i < v.size(); i++)
			sarr[i] = new StringBuilder(v.elementAt(i));
		int num = trie.getcnt();
		for (int i = 0; i < sarr.length; i++) {
			for (int j = 0; j < sarr[i].length(); j++)
				if (sarr[i].charAt(j) >= 'A' && sarr[i].charAt(j) <= 'Z')
					sarr[i].setCharAt(j, (char)(sarr[i].charAt(j) - ('A' - 'a')));
			int u = trie.search(sarr[i].toString());
			inx.add(u == -1 ? i + num : u);
		}
		Vector<Integer> vi = graph.insertBridge(inx);
		String ret = new String();
		for (int i = 0; i < vi.size(); i++) {
			if (i > 0)
				ret += " ";
			ret += v.elementAt(i);
			if (vi.elementAt(i) != -1)
				ret += " " + trie.getString(vi.elementAt(i));
		}
		return ret;
	}

	String calShortestPath(String word1, String word2) {
		int w1 = trie.search(word1), w2 = trie.search(word2);
		if (w1 == -1 || w2 == -1)	return null;
		Vector<int[]> vi = graph.getPath(w1, w2);
		String ret = new String("");
		for (int i = 0; i < vi.size(); i++) {
			String tmp = new String("");
			for (int j = 0; j < vi.elementAt(i).length; j++) {
				if (j > 0)
					tmp += " ";
				tmp += trie.getString(vi.elementAt(i)[j]);
			}
			ret+=tmp;
			ret+="\n";
		}
		return ret;
	}
	
	String randomWalk() {
		Vector<Integer> v = graph.randomWalk();
		String ret = new String("");
		for (int i = 0; i < v.size(); i++) {
			if (i > 0)	ret += " ";
			ret += trie.getString(v.elementAt(i));
		}
		return ret;
	}
	
	Vector<String> getEdges() {
		Vector<int[]> iv = graph.getEdges();
		Vector<String> ret = new Vector<String>();
		for (int i = 0; i < iv.size(); i++) {
			int u = iv.elementAt(i)[0], v = iv.elementAt(i)[1];
			String tmp = new String("");
			tmp += trie.getString(u);
			tmp += "->";
			tmp += trie.getString(v);
			tmp=tmp+"[label=\"" + iv.elementAt(i)[2]+"\"]";
			ret.add(tmp);
		}
		return ret;
	}
	
}