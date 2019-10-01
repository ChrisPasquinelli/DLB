/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class DLB {

	private final char TERMINATE_CHAR = '.'; 
	Node rootNode;	

        public DLB(){
		rootNode = new Node();
	}

	public boolean add(String s)
	{
		s = s + TERMINATE_CHAR;
		Node currentNode = rootNode;
		boolean added = false;

		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			NodeAddResult result = addChildNode(currentNode, c);
			currentNode = result.node;
			added = result.added; 
		}
		return added;
	}
        
	public ArrayList search(StringBuilder key){ 
		Node currentNode = rootNode;
		for (int i = 0; i < key.length(); i++){
			char c = key.charAt(i);
			currentNode = getChildNode(currentNode, c);
			if (currentNode == null){
				return new ArrayList();
			}
		}
                ArrayList list = new ArrayList();
                searcher(list, currentNode.childNode, key.toString());
                return list;
	}
        private void searcher(ArrayList list, Node n, String sb){
     //       System.out.println("sb: "+sb);
            if (list.size()==6)
                return;
            if(n.value == TERMINATE_CHAR){
                list.add(sb);
           
            }
            else
                sb+=n.value;
            if (n.childNode!=null)
                searcher(list,n.childNode, sb);
            
            while(n.peerNode != null){
                searcher(list, n.peerNode, sb);
                n = n.peerNode;
            }
            
        }
	private Node getPeerNode(Node peerStart, char c){
		Node nextPeer = peerStart;
		while (nextPeer != null){

			if (nextPeer.value == c) break;

			nextPeer = nextPeer.peerNode;
		}
		return nextPeer;
	}

	private Node getChildNode(Node parentNode, char c){
		return getPeerNode(parentNode.childNode, c);
	}

	private NodeAddResult addPeerNode(Node peerStart, char c){
		if (peerStart == null){
			peerStart = new Node(c);
			return new NodeAddResult(peerStart, true);
		}
		else{
                    Node nextPeer = peerStart;
			while (nextPeer.peerNode != null){
                            if (nextPeer.value == c) break;
                                nextPeer = nextPeer.peerNode;
			}
			if (nextPeer.value == c){

                            return new NodeAddResult(nextPeer, false);
			}
			else{

				nextPeer.peerNode = new Node(c);
				return new NodeAddResult(nextPeer.peerNode, true);
			}
		}
	}

	private NodeAddResult addChildNode(Node parentNode, char c){
		if (parentNode.childNode == null){
			parentNode.childNode = new Node(c);
			return new NodeAddResult(parentNode.childNode, true);
                }
		else{
			return addPeerNode(parentNode.childNode, c);
		}
	}
        
        //used to print DLB for debugging
        public void printDLB()
        {
            printNode(rootNode);
        }
        
        public void printNode(Node n)
        {
            while (n.childNode!=null)
            {
                System.out.print("-> "+n.childNode.value);
                n=n.childNode;
            }
            System.out.println("");
            while(n.peerNode != null){
                printNode(n.peerNode);
                n = n.peerNode;
            }
            
        }
}

class Node{
	Node peerNode;
	Node childNode;
	char value;
        
	public Node(){
        }

        public Node(char value)	{
		this(value, null, null);
	}
        
	public Node(char value, Node peerNode, Node childNode){
		this.value = value;
		this.peerNode = peerNode; //sibling node
		this.childNode = childNode;
	}
}

class NodeAddResult
{
	Node node;
	boolean added;
        
	public NodeAddResult(Node node, boolean added){
		this.node = node;
		this.added = added;
	}
}