package part2;

import adt.*;

import java.util.*;

public class ExpenditureTrees {

	/**
	 * This method takes a tree describing a hierarchical classification scheme
	 * of expenditures, and a list of expenditure types from that tree and
	 * returns the <b>summary</b> of that list of types (as described in the
	 * assignment handout). The summary is calculated with respect to the given
	 * tree.
	 * 
	 * The expenditure types in the tree and the list are denoted by non-null
	 * Strings. Two types are the same if their String representations are equal
	 * (using the ".equals()" method). The same type may not occur in two
	 * different positions in the tree. The same type may not appear twice in
	 * the given list of types either. Each type in the list should be equal to
	 * one in the tree.
	 * 
	 * This method assumes that the parameters tree and types are non-null and
	 * that the tree is non-empty. The list of types may be an empty list.
	 * 
	 * The <b>summary</b> of the list of types is returned by the method as a
	 * non-null list of expenditure types. The returned list should not contain
	 * duplicate types (since it denotes a set).
	 * 
	 * This method should not modify its parameters in any way.
	 */
	public static List<String> summary(Tree<String> tree, List<String> types) {
		// list of the summary of the given types
		List<String> summary = summaryList(tree, types, tree.root());
		return summary;
	}

	/**
	 * This method takes a tree, list of types and a node and returns the
	 * summary of the given types.
	 */
	private static List<String> summaryList(Tree<String> tree,
			List<String> types, Position<String> node) {
		// list of the summary of the given types
		List<String> summary = new ArrayList<String>();
		if (tree.isInternal(node)) {
			// boolean to see if all the node's children's types are in the list
			boolean allChildren = true;
			// add the types found from the node's children to the list
			for (int i = 0; i < tree.numChildren(node); i++) {
				List<String> temp = summaryList(tree, types, tree
						.children(node).get(i));
				for (int n = 0; n < temp.size(); n++) {
					summary.add(temp.get(n));
				}
				// check if child's type is in the list
				if (!summary.contains(tree.children(node).get(i).getElement())) {
					allChildren = false;
				}
			}
			// remove all children's types from the list and add the current
			// node's type if all children's type are in the list or if the
			// current node's type is part of the given types
			if (allChildren || types.contains(node.getElement())) {
				for (int i = 0; i < tree.numChildren(node); i++) {
					summary.remove(tree.children(node).get(i).getElement());
				}
				summary.add(node.getElement());
			}

		} else {
			// add node's type to list if it is part of the given types
			if (types.contains(node.getElement())) {
				summary.add(node.getElement());
			}
		}
		return summary;
	}

	/**
	 * This method takes a tree describing a hierarchical classification scheme
	 * of expenditures, a <b>concise</b> list of expenditure types from that
	 * tree, and an expenditure type from the tree and returns true if the given
	 * expenditure type is in the <b>closure</b> of the concise list of types,
	 * and false otherwise. (See the assignment handout for definitions.)
	 * 
	 * The expenditure types in the tree and the list are denoted by non-null
	 * Strings. Two types are the same if their String representations are equal
	 * (using the ".equals()" method). The same type may not occur in two
	 * different positions in the tree. The same type may not appear twice in
	 * the given list of types either. Each type in the list should be equal to
	 * one in the tree. The given expenseType should also be equal to one in the
	 * tree.
	 * 
	 * This method assumes that the parameters tree, types and expenseType are
	 * non-null and that the tree is non-empty. The list of types may be an
	 * empty list.
	 * 
	 * This method should not modify its parameters in any way.
	 */
	public static boolean contains(Tree<String> tree, List<String> types,
			String expenseType) {
		Boolean contains = false;
		// list of the closure of the given types
		List<String> typesList = typesList(tree, types, tree.root());
		// check if given expense type is in the list or if the given types
		// include the root's type
		if (typesList.contains(expenseType)
				|| types.contains(tree.root().getElement())) {
			contains = true;
		}
		return contains;
	}

	/**
	 * @require !types.contains(tree.root().getElement())
	 * 
	 * This method takes a tree, list of types and a node and returns a
	 * list of all the nodes in the closure of the given types.
	 */
	private static List<String> typesList(Tree<String> tree,
			List<String> types, Position<String> node) {
		// list of the closure of the given types
		List<String> typesList = new ArrayList<String>();
		if (tree.isInternal(node)) {
			// add the types found from the node's children to the list
			for (int i = 0; i < tree.numChildren(node); i++) {
				List<String> temp = new ArrayList<String>();
				// check if the child's type is part of the given types
				if (types.contains(tree.children(node).get(i).getElement())) {
					temp = allTypes(tree, tree.children(node).get(i));
				} else {
					temp = typesList(tree, types, tree.children(node).get(i));
				}
				for (int n = 0; n < temp.size(); n++) {
					typesList.add(temp.get(n));
				}
			}
		}
		return typesList;
	}

	/**
	 * This method takes a tree and a node and returns a list of all the nodes
	 * in the closure of the given node.
	 */
	private static List<String> allTypes(Tree<String> tree,
			Position<String> node) {
		// list of the closure of the given node's type
		List<String> allTypes = new ArrayList<String>();
		if (tree.isInternal(node)) {
			// add the types found from the node's children to the list
			for (int i = 0; i < tree.numChildren(node); i++) {
				List<String> temp = allTypes(tree, tree.children(node).get(i));
				for (int n = 0; n < temp.size(); n++) {
					allTypes.add(temp.get(n));
				}
			}
			allTypes.add(node.getElement());
		} else {
			// add the node's type to the list
			allTypes.add(node.getElement());
		}
		return allTypes;
	}

	/**
	 * This method takes a tree describing a hierarchical classification scheme
	 * of expenditures, and a list of expenditure types from that tree and
	 * returns the <b>negation</b> of that list of types (as described in the
	 * assignment handout). The negation is calculated with respect to the given
	 * tree.
	 * 
	 * The expenditure types in the tree and the list are denoted by non-null
	 * Strings. Two types are the same if their String representations are equal
	 * (using the ".equals()" method). The same type may not occur in two
	 * different positions in the tree. The same type may not appear twice in
	 * the given list of types either. Each type in the list should be equal to
	 * one in the tree.
	 * 
	 * This method assumes that the parameters tree and types are non-null and
	 * that the tree is non-empty. The list of types may be an empty list.
	 * 
	 * The <b>negation</b> of the list of types is returned by the method as a
	 * non-null list of expenditure types. The returned list should not contain
	 * duplicate types (since it denotes a set).
	 * 
	 * This method should not modify its parameters in any way.
	 */
	public static List<String> negation(Tree<String> tree, List<String> types) {
		// list of the negation of the given types
		List<String> negation = new ArrayList<String>();
		// checks if the given types exclude the root's type
		if (!types.contains(tree.root().getElement())) {
			negation = negationList(tree, types, tree.root());
		}
		return negation;
	}

	/**
	 * @require !types.contains(tree.root().getElement())
	 * 
	 * This method takes a tree, list of types and a node and returns a
	 * list of the negation of the given types.
	 */
	private static List<String> negationList(Tree<String> tree,
			List<String> types, Position<String> node) {
		// list of the negation of the given types
		List<String> negation = new ArrayList<String>();
		if (tree.isInternal(node)) {
			// boolean to see if all the node's children's types are in the list
			boolean allChildren = true;
			// add the types found from the node's children (whose type is not
			// in the given types) to the list
			for (int i = 0; i < tree.numChildren(node); i++) {
				if (!types.contains(tree.children(node).get(i).getElement())) {
					List<String> temp = negationList(tree, types, tree
							.children(node).get(i));
					for (int n = 0; n < temp.size(); n++) {
						negation.add(temp.get(n));
					}
				}
				// check if child's type is in the list
				if (!negation.contains(tree.children(node).get(i).getElement())) {
					allChildren = false;
				}
			}
			// remove all children's types from the list and add the current
			// node's type if all children's type are in the list
			if (allChildren) {
				for (int i = 0; i < tree.numChildren(node); i++) {
					negation.remove(tree.children(node).get(i).getElement());
				}
				negation.add(node.getElement());
			}
		} else {
			// add node's type to list if it's not part of the given list
			if (!types.contains(node.getElement())) {
				negation.add(node.getElement());
			}
		}
		return negation;
	}

}
