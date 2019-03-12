package br.pre.crackcodew.chap4.treegraph;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <b>Build Order</b>: You are given a list of projects and a list of
 * dependencies (which is a list of pairs of projects, where the second project
 * is dependent on the first project). All of a project's dependencies must be
 * built before the project is. Find a build order that will allow the projects
 * to be built. If there is no valid build order, return an error. <br/>
 * EXAMPLE<br/>
 * Input:<br/>
 * projects: a, b, c, d, e, f<br/>
 * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)<br/>
 * Output: f, e, a, b, d, c
 * 
 * @author bbadarch
 *
 */
public class Prob4_7 {

	/*
	 * Build the graph, adding the edge (a, b) if b is dependent on a. Assumes a
	 * pair is listed in “build order”. The pair (a, b) in dependencies indicates
	 * that b depends on a and a must be built before b.
	 */
	public static Graphh buildGraph(String[] projects, String[][] dependencies) {
		Graphh graph = new Graphh();
		for (String project : projects) {
			graph.getOrCreateNode(project);
		}

		for (String[] dependency : dependencies) {
			String first = dependency[0];
			String second = dependency[1];
			graph.addEdge(first, second);
		}

		return graph;
	}

	/*
	 * A helper function to insert projects with zero dependencies into the order
	 * array, starting at index offset.
	 */
	public static int addNonDependent(Project[] order, ArrayList<Project> projects, int offset) {
		for (Project project : projects) {
			if (project.getNumberDependencies() == 0) {
				order[offset] = project;
				offset++;
			}
		}
		return offset;
	}

	public static Project[] orderProjects(ArrayList<Project> projects) {
		Project[] order = new Project[projects.size()];

		/* Add “roots” to the build order first. */
		int endOfList = addNonDependent(order, projects, 0);

		int toBeProcessed = 0;
		while (toBeProcessed < order.length) {
			Project current = order[toBeProcessed];

			/*
			 * We have a circular dependency since there are no remaining projects with zero
			 * dependencies.
			 */
			if (current == null) {
				return null;
			}

			/* Remove myself as a dependency. */
			ArrayList<Project> children = current.getChildren();
			for (Project child : children) {
				child.decrementDependencies();
			}

			/* Add children that have no one depending on them. */
			endOfList = addNonDependent(order, children, endOfList);

			toBeProcessed++;
		}

		return order;
	}

	public static String[] convertToStringList(Project[] projects) {
		String[] buildOrder = new String[projects.length];
		for (int i = 0; i < projects.length; i++) {
			buildOrder[i] = projects[i].getName();
		}
		return buildOrder;
	}

	public static Project[] findBuildOrder(String[] projects, String[][] dependencies) {
		Graphh graph = buildGraph(projects, dependencies);
		return orderProjects(graph.getNodes());
	}

	public static String[] buildOrderWrapper(String[] projects, String[][] dependencies) {
		Project[] buildOrder = findBuildOrder(projects, dependencies);
		if (buildOrder == null)
			return null;
		String[] buildOrderString = convertToStringList(buildOrder);
		return buildOrderString;
	}

	public static void main(String[] args) {
		String[] projects = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };
		String[][] dependencies = { { "a", "b" }, { "b", "c" }, { "a", "c" }, { "a", "c" }, { "d", "e" }, { "b", "d" }, { "e", "f" }, { "a", "f" },
				{ "h", "i" }, { "h", "j" }, { "i", "j" }, { "g", "j" } };
		String[] buildOrder = buildOrderWrapper(projects, dependencies);
		if (buildOrder == null) {
			System.out.println("Circular Dependency.");
		} else {
			for (String s : buildOrder) {
				System.out.println(s);
			}
		}
	}
}

class Graphh {
	private ArrayList<Project> nodes = new ArrayList<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();

	public Project getOrCreateNode(String name) {
		if (!map.containsKey(name)) {
			Project node = new Project(name);
			nodes.add(node);
			map.put(name, node);
		}

		return map.get(name);
	}

	public void addEdge(String startName, String endName) {
		Project start = getOrCreateNode(startName);
		Project end = getOrCreateNode(endName);
		start.addNeighbor(end);
	}

	public ArrayList<Project> getNodes() {
		return nodes;
	}
}

class Project {
	private ArrayList<Project> children = new ArrayList<Project>();
	private HashMap<String, Project> map = new HashMap<String, Project>();
	private String name;
	private int dependencies = 0;

	public Project(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void addNeighbor(Project node) {
		if (!map.containsKey(node.getName())) {
			children.add(node);
			map.put(node.getName(), node);
			node.incrementDependencies();
		}
	}

	public void incrementDependencies() {
		dependencies++;
	}

	public ArrayList<Project> getChildren() {
		return children;
	}

	public void decrementDependencies() {
		dependencies--;
	}

	public int getNumberDependencies() {
		return dependencies;
	}
}