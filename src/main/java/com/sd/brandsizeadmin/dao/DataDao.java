package com.sd.brandsizeadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.brandsize.startup.PropertyLoader;

public class DataDao {
        private Connection connection;
        private PropertyLoader loader ;
        
        public DataDao() throws Exception {
                /*connection = DBUtility.getConnection();*/
        	loader = new PropertyLoader();
        	try 
            {
            Class.forName(loader.getProperty("DRIVER"));
            // set the url, username and password for the database
            connection = DriverManager.getConnection(loader.getProperty("COMSDB_URL"), loader.getProperty("COMSUSERNAME"), loader.getProperty("COMSPASSWORD"));
            } catch (Exception e) {
                    e.printStackTrace();
            }
        }

        //public ArrayList<String> getFrameWork() {
		public String getFrameWork() {
                ArrayList<String> list = new ArrayList<String>();
                String FinalCatList = "";
                PreparedStatement ps = null;
                String data;
                try {
                        /*ps = connection.prepareStatement("SELECT cat.name AS cat_name,subcat.name AS subcat_name,prodtype.name AS prodtype_name,prodtype.id,prodtype.category_url FROM coms_category cat JOIN coms_category subcat ON cat.id = subcat.parent_category_id JOIN coms_category prodtype ON subcat.id = prodtype.parent_category_id WHERE prodtype.invisible = 0 and cat.invisible = 0 and subcat.invisible = 0 and cat.name like ? or subcat.name like ? or prodtype.name like ? AND cat.parent_category_id IS NULL  ORDER BY cat_name limit 10");//("select brand_name from brand where brand_name like ?");
                	
                        ps.setString(1, "%" + name + "%");
                        ps.setString(2, "%" + name + "%");
                        ps.setString(3, "%" + name + "%");*/
                		ps = connection.prepareStatement("SELECT cat.name AS cat_name,subcat.name AS subcat_name,prodtype.name AS prodtype_name,prodtype.id,prodtype.category_url FROM coms_category cat JOIN coms_category subcat ON cat.id = subcat.parent_category_id JOIN coms_category prodtype ON subcat.id = prodtype.parent_category_id WHERE prodtype.invisible = 0 and cat.invisible = 0 and subcat.invisible = 0 and prodtype.deleted_time > now() and subcat.deleted_time > now() and cat.deleted_time > now()and cat.parent_category_id IS NULL  ORDER BY cat_name");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("data fetched is "+rs.getFetchSize());
                        while (rs.next()) {
                            data = rs.getString("cat_name")+loader.getProperty("cat_subcat_separator")+rs.getString("subcat_name")+loader.getProperty("cat_subcat_separator")+rs.getString("prodtype_name");
                            //System.out.println(data);
                            if (FinalCatList.equals(""))
                            	FinalCatList = data;
                            else
                            {
                            	//System.out.println("inside else of datadao");
                            	FinalCatList = FinalCatList.concat(":"+data);
                            }
                            list.add(data);
                    		}
                        /*System.out.println("data list");
                        System.out.println(list.size());*/
                        connection.close();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        
                }
                
                //return list;
                //System.out.println("final category list :"+FinalCatList);
                return FinalCatList;
        }
}
