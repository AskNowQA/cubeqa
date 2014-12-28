package org.aksw.autosparql.cube;

import java.util.List;
import edu.stanford.nlp.trees.Tree;

/** Utility class for stanford trees.
 * @author Konrad Höffner */
public class Trees
{
	static public String phrase(Tree tree) {return tree.getLeaves().toString().replace(", ", " ").replaceAll("[\\[\\]]", "");}

	// tree.remove is unsupported
	static public void removeChild(Tree tree, Tree child)
	{
		List<Tree> children = tree.getChildrenAsList();
		children.remove(child);
		tree.setChildren(children);
	}

	static public void removeSubtree(Tree tree, Tree child)
	{
		List<Tree> children = tree.getChildrenAsList();
		children.remove(child);
		tree.setChildren(children);
		for(Tree subTree: tree.getChildrenAsList()) removeSubtree(subTree, child);
	}
}