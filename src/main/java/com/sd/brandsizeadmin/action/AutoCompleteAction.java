package com.sd.brandsizeadmin.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.Action;
import com.sd.brandsizeadmin.dao.DataDao;

public class AutoCompleteAction implements Action {
        // Received via Ajax request
        private String term;
        private String name;
        // Returned as response
        //private ArrayList<String> list;
        private String list;
        
        public String execute() {
                try {
                        System.out.println("Parameter from ajax request : - " + term);
                        System.out.println("name is "+name);
                        DataDao dataDao = new DataDao();
                        //list = dataDao.getFrameWork(term.trim());
                        setList(dataDao.getFrameWork());
                } catch (Exception e) {
                        System.err.println(e.getMessage());
                }
                return SUCCESS;
        }

       /* public ArrayList<String> getList() {
                return list;
        }

        public void setList(ArrayList<String> list) {
                this.list = list;
        }*/

        public String getTerm() {
                return term;
        }

        public void setTerm(String term) {
                this.term = term;
        }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getList() {
			return list;
		}

		public void setList(String list) {
			this.list = list;
		}
}