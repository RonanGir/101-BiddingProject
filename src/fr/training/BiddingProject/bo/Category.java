package fr.training.BiddingProject.bo;

import java.util.ArrayList;
import java.util.List;

public class Category {
	
	private int noCategory;
	private String name;
	private List<SoldArticle> listCategoryArticle;
	
	public Category() {
		listCategoryArticle = new ArrayList<>();
	}

	public Category(int noCategory, String name) {
		super();
		listCategoryArticle = new ArrayList<>();
		this.noCategory = noCategory;
		this.name = name;
	}

	public int getNoCategory() {
		return noCategory;
	}

	public void setNoCategory(int noCategory) {
		this.noCategory = noCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SoldArticle> getListCategoryArticle() {
		return listCategoryArticle;
	}

	public void setListCategoryArticle(List<SoldArticle> listCategoryArticle) {
		this.listCategoryArticle = listCategoryArticle;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Catégorie \n \t noCategorie = ");
		sb.append(noCategory);
		sb.append("\n \t Libelle = ");
		sb.append(name);
		sb.append("\n \t listCategoryArticle = ");
		sb.append(listCategoryArticle);
		return sb.toString();
	}
	
	

}
