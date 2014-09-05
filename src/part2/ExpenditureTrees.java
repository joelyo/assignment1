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
		List<String>summary = summaryList(tree, types, tree.root());
		return summary;
	}
	
	private static List<String> summaryList(Tree<String> tree, List<String> types, Position<String> node) {
		List<String>summary = new ArrayList<String>();
		if (types.contains(node.getElement())) {
			summary.add(node.getElement());
		}
		if (tree.isInternal(node)) {
			for (int i = 0; i < tree.numChildren(node); i++) {
				List<String>temp = summaryList(tree, types, tree.children(node).get(i));
				System.out.println(temp);
				for (int n = 0; n < temp.size(); n++) {
					summary.add(temp.get(n));
				}
			}
			if (summary.contains(node.getElement())) {
				for (int i = 0; i < tree.numChildren(node); i++) {
					summary.remove(tree.children(node).get(i).getElement());
				}
			}
			boolean allChildren = true;
			for (int i = 0; i < tree.numChildren(node); i++) {
				if (!summary.contains(tree.children(node).get(i).getElement())) {
					allChildren = false;
				}
			}
			if (allChildren) {
				for (int i = 0; i < tree.numChildren(node); i++) {
					summary.remove(tree.children(node).get(i).getElement());
				}
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
		return false; // REMOVE THIS LINE AND WRITE THIS METHOD
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
		return null; // REMOVE THIS LINE AND WRITE THIS METHOD
	}

}
