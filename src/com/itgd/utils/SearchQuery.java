package com.itgd.utils;

public class SearchQuery {
    public String getQyery(String text, String search_type,
        String searchphrase, String section, String fromdate, String todate,
        String date_type, String ordering, String category,String removeText) {
        String searchText = "";
        StringBuffer sb = null;        
        sb=new StringBuffer();
        sb.append("SELECT a.id   AS articleid,  a.sef_url   AS sef_url,  a.title     AS title,  a.introtext AS introtext,u.id AS id,u.name  AS section ,a.byline    AS byline,a.catid as catid,a.city as city,date_format( a.created,'%M %e, %Y' ) AS publishdate,u.sef_url  AS section_sefUrl, a.mobile_image as thumbimage, a.kicker_image_alt_text as thumbimagealttext");
        
        if ((category != null) && !category.equalsIgnoreCase("null") &&
                !category.equalsIgnoreCase("")) {
        	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u ,jos_categories as cat where ");
        }else{
        	
        	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u  where ");
        }
      

        if ((text != null) && !text.equalsIgnoreCase("")) {
        	
            searchText = text.trim();

            if ((searchphrase != null) && !searchphrase.equalsIgnoreCase("") &&
                    !searchphrase.equalsIgnoreCase("null")) {
                if (searchphrase.equalsIgnoreCase("any")) {
                    if (searchText.contains(" ")) {
                        String searText = "";
                        String text1 = "";
                        String[] tokens = text.split(" ");
                        String finalStr = "";
                        for (int i = 0; i < tokens.length; i++) {
                        searText = tokens[i];
                        String[] textRemoved = removeText.split("\\#");
                        for (int j = 0; j < textRemoved.length; j++) {
                          if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                        	  tokens[i] = "";
                        	  }
                          }
                        if (tokens[i] != "")
                        	finalStr = finalStr + tokens[i] + " "; 
                        }
                        finalStr=finalStr.trim();
                        //System.out.println("finalStr"+finalStr);
                        if (finalStr.contains(" ")) {                         	
                        	sb.append("(");
                            String[] tokens1 = finalStr.split(" ");
                            for (int k = 0; k < tokens1.length; k++) {
                            	finalStr = tokens1[k];
                                sb.append("MATCH(a.fulltext) AGAINST ('" + finalStr + "')  or a.introtext like '" + "%" +
                                    finalStr + "%" +
                                    "' or a.title_alias like  '" + "%" +
                                    finalStr + "%" + "' or a.title like  '" +
                                    "%" + finalStr + "%" + "' or a.byline like  '" +
                                    "%" + finalStr + "%" + "'");
                                if (k != (tokens1.length - 1)) {
                                    sb.append("or ");
                                }
                            }
                            sb.append(")");
                        	
                        	
                        } else {
                            sb.append("(MATCH(a.fulltext) AGAINST ('" + finalStr + "')  or a.introtext like '" + "%" + finalStr +
                                    "%" + "' or a.title_alias like  '" + "%" +
                                    finalStr + "%" + "' or a.title like  '" + "%" +
                                    finalStr + "%" + "' or a.byline like  '" +
                                    "%" + finalStr + "%" + "')");
                            }
                        } else {
                        sb.append("(MATCH(a.fulltext) AGAINST ('" + searchText + "')  or a.introtext like '" + "%" + searchText +
                            "%" + "' or a.title_alias like  '" + "%" +
                            searchText + "%" + "' or a.title like  '" + "%" +
                            searchText + "%" + "' or a.byline like  '" +
                                    "%" + searchText + "%" + "')");
                    }
                } else if (searchphrase.equalsIgnoreCase("all")) {
                    if (searchText.contains(" ")) {
                       
                    	String searText = "";
                        String text1 = "";
                        String[] tokens = text.split(" ");
                        String finalStr = "";
                        for (int i = 0; i < tokens.length; i++) {
                        searText = tokens[i];
                        String[] textRemoved = removeText.split("\\#");
                        for (int j = 0; j < textRemoved.length; j++) {
                          if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                        	  tokens[i] = "";
                        	  }
                          }
                        if (tokens[i] != "")
                        	finalStr = finalStr + tokens[i] + " "; 
                        }
                        finalStr=finalStr.trim();                   	
                        
                    	
                        if (finalStr.contains(" ")) {                         	
                        	sb.append("(");
                            String[] tokens2 = finalStr.split(" ");
                            for (int l = 0; l < tokens2.length; l++) {
                            	finalStr = tokens2[l];
                                sb.append("(MATCH(a.fulltext) AGAINST ('" + finalStr + "')  or a.title_alias like  '" + "%" +finalStr + "%" + "' or a.introtext like '" + "%" + finalStr +
                            "%" + "' or a.title like '" + "%" + finalStr +
                            "%" + "' or a.byline like  '" +
                                    "%" + finalStr + "%" + "')");

                                if (l != (tokens2.length - 1)) {
                                    sb.append("and ");
                                }
                            }
                            sb.append(")");
                        	
                        	
                        } else {
                            sb.append("(MATCH(a.fulltext) AGAINST ('" + finalStr + "')  or a.introtext like '" + "%" + finalStr +
                                    "%" + "' or a.title_alias like  '" + "%" +
                                    finalStr + "%" + "' or a.title like  '" + "%" +
                                    finalStr + "%" + "' or a.byline like  '" +
                                    "%" + finalStr + "%" + "')");
                            }
                    } else {
                        sb.append("(MATCH(a.fulltext) AGAINST ('" + searchText + "') or  a.introtext like '" + "%" + searchText +
                            "%" + "' or a.title_alias like  '" + "%" +
                            searchText + "%" + "' or a.title like  '" + "%" +
                            searchText + "%" + "' or a.byline like  '" +
                                    "%" + searchText + "%" + "')");
                    }
                } else if (searchphrase.equalsIgnoreCase("exact")) {
                    sb.append("(MATCH(a.fulltext) AGAINST ('" + searchText + "') or  a.introtext like '" + "%" + searchText + "%" +
                        "'  or a.title_alias like  '" + "%" + searchText + "%" +
                        "' or a.title like  '" + "%" + searchText + "%" + "' or a.byline like  '" +
                                    "%" + searchText + "%" + "')");
                }
            } else {
            	
            	//System.out.println("----------/@@@@@@@@/------------");
            	/*
                if (searchText.contains(" ")) {
                	
                	
                	String searText = "";
                    String text1 = "";
                    String[] tokens = text.split(" ");
                    String finalStr = "";
                    for (int i = 0; i < tokens.length; i++) {
                    searText = tokens[i];
                    String[] textRemoved = removeText.split("\\#");
                    for (int j = 0; j < textRemoved.length; j++) {
                      if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                    	  tokens[i] = "";
                    	  }
                      }
                    if (tokens[i] != "")
                    	finalStr = finalStr + tokens[i] + " "; 
                    }
                    finalStr=finalStr.trim();
                    
                    if (finalStr.contains(" ")) {                         	
                    	sb.append("(");
                        String[] tokens4 = searchText.split(" ");
                        for (int m = 0; m < tokens4.length; m++) {
                            searchText = tokens4[m];
                            sb.append("a.fulltext like '" + "%" + searchText + "%" +
                                "' or introtext like '" + "%" + searchText + "%" +
                                "' or title_alias like  '" + "%" + searchText +
                                "%" + "' or a.byline like  '" +
                                    "%" + searchText + "%" + "' or a.title like  '" +
                                    "%" + searchText + "%" + "'");

                            if (m != (tokens.length - 1)) {
                                sb.append("or ");
                            }
                        }

                        sb.append(")");
                    	
                    	
                    } else {
                        sb.append("(a.fulltext like '" + "%" + searchText + "%" +
                                "' or introtext like '" + "%" + searchText + "%" +
                                "' or title_alias like  '" + "%" + searchText + "%" +
                                "' or a.byline like  '" +
                                    "%" + searchText + "%" + "' or a.title like  '" +
                                    "%" + searchText + "%" + "')");
                        }
                } else {
                    sb.append("(a.fulltext like '" + "%" + searchText + "%" +
                        "' or introtext like '" + "%" + searchText + "%" +
                        "' or title_alias like  '" + "%" + searchText + "%" +
                        "' or a.byline like  '" +
                                    "%" + searchText + "%" + "' or a.title like  '" +
                                    "%" + searchText + "%" + "')");
                }
            */

                if (searchText.contains(" ")) {
                   
                	String searText = "";
                    String text1 = "";
                    String[] tokens = text.split(" ");
                    String finalStr = "";
                    for (int i = 0; i < tokens.length; i++) {
                    searText = tokens[i];
                    String[] textRemoved = removeText.split("\\#");
                    for (int j = 0; j < textRemoved.length; j++) {
                      if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                    	  tokens[i] = "";
                    	  }
                      }
                    if (tokens[i] != "")
                    	finalStr = finalStr + tokens[i] + " "; 
                    }
                    finalStr=finalStr.trim();
                	
                    
                	
                    if (finalStr.contains(" ")) {                         	
                    	sb.append("(");
                        String[] tokens2 = finalStr.split(" ");
                        for (int l = 0; l < tokens2.length; l++) {
                        	finalStr = tokens2[l];
                            sb.append("(a.title_alias like  '" + "%" +finalStr + "%" + "' or a.introtext like '" + "%" + finalStr +
                        "%" + "' or a.title like '" + "%" + finalStr +
                        "%" + "' or a.byline like  '" +
                                "%" + finalStr + "%" + "')");

                            if (l != (tokens2.length - 1)) {
                                sb.append("and ");
                            }
                        }
                        sb.append(")");
                    	
                    	
                    } else {
                        sb.append("(a.introtext like '" + "%" + finalStr +
                                "%" + "' or a.title_alias like  '" + "%" +
                                finalStr + "%" + "' or a.title like  '" + "%" +
                                finalStr + "%" + "' or a.byline like  '" +
                                "%" + finalStr + "%" + "')");
                        }
                } else {
                    sb.append("(a.introtext like '" + "%" + searchText +
                        "%" + "' or a.title_alias like  '" + "%" +
                        searchText + "%" + "' or a.title like  '" + "%" +
                        searchText + "%" + "' or a.byline like  '" +
                                "%" + searchText + "%" + "')");
                }
            	
            
            }
        }

        if ((search_type != null) && !search_type.equalsIgnoreCase("") &&
                !search_type.equalsIgnoreCase("null")) {
            if (search_type.equalsIgnoreCase("author")) {
            	sb= new StringBuffer();
                sb.append("SELECT a.id        AS articleid,  a.sef_url   AS sef_url,  a.title     AS title,  a.introtext AS introtext,  u.id        AS id,  u.name      AS section ,a.byline    AS byline ,a.catid as catid,a.city as city,date_format( a.created,'%M %e, %Y' ) AS publishdate,u.sef_url  AS section_sefUrl, a.mobile_image as thumbimage, a.kicker_image_alt_text as thumbimagealttext");
                if ((category != null) && !category.equalsIgnoreCase("null") &&
                        !category.equalsIgnoreCase("")) {
                	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u ,jos_categories as cat where ");
                }else{
                	
                	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u  where ");
                }
              sb.append("  a.byline like '" + "%" + text  + "%" + "'");
            }
        }

        // Check for the section
        if ((section != null) && !section.equalsIgnoreCase("") &&
                !section.equalsIgnoreCase("null")) {
            sb.append(" AND u.id = '" + section + "'");
        }

        // Check for the form date
        if ((fromdate != null) && (todate != null) &&
                !fromdate.equalsIgnoreCase("null") &&
                !todate.equalsIgnoreCase("null") &&
                !fromdate.equalsIgnoreCase("") && !todate.equalsIgnoreCase("")) {
            sb.append(" AND date(a.created) BETWEEN '" + fromdate + "' AND '" +
                todate + "'");
        } else {
           
        	sb.append("");
        }

        // Check for the Category
        if ((category != null) && !category.equalsIgnoreCase("null") &&
                !category.equalsIgnoreCase("")) {
            sb.append(" AND a.catid = '" + category + "'");
        }

        if ((ordering != null) && !ordering.equalsIgnoreCase("null") &&
                !ordering.equalsIgnoreCase("")) {
            if (ordering.equalsIgnoreCase("oldest")) {
                ordering = "a.created ASC";
            } else if (ordering.equalsIgnoreCase("alpha")) {
                ordering = "a.title_alias ASC";
            } else if (ordering.equalsIgnoreCase("newest")) {
                ordering = "a.created DESC";
            } else {
                ordering = "a.created DESC";
            }
        } else {
            ordering = "a.created DESC";
        }

        //sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 and u.id=cat.section and a.primary_category!='86'  GROUP BY a.id ORDER BY  " +ordering + "");
        
     
       // sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 AND a.primary_category NOT LIKE '86%' AND a.primary_category NOT LIKE '87%' ");
        //sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 AND ars.section_id not in(86,87,62,0)");
        sb.append("AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 AND a.content_type=0 ");
    
        
       /* if ((category != null) && !category.equalsIgnoreCase("null") &&
                !category.equalsIgnoreCase("")) {
            sb.append(" AND a.primary_category  LIKE '" + section + "#" + category + "%'");
        }*/
   
        sb.append(" GROUP BY a.id ORDER BY  " +ordering + " ");
        String query = sb.toString();
        //System.out.println("search query---------->"+query);

        return query;
    }
    
    
    public String getVideoQyery(String text, String search_type,
            String searchphrase, String section, String fromdate, String todate,
            String date_type, String ordering, String category,String removeText) {
    	
    	//System.out.println("getVideoQyery Executed ");
            String searchText = "";
            StringBuffer sb = null;        
            sb=new StringBuffer();
            sb.append("SELECT   a.id        AS articleid,  a.sef_url   AS sef_url,  a.title     AS title,  a.introtext AS introtext,u.id AS id,u.name  AS section ,a.byline AS byline,a.catid as catid,a.city as city,date_format( a.created,'%M %e, %Y' ) AS publishdate,u.sef_url  AS section_sefUrl, a.mobile_image as thumbimage, a.kicker_image_alt_text as thumbimagealttext");
            
            if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
            	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u ,jos_categories as cat where ");
            }else{
            	
            	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u  where ");
            }
          

            if ((text != null) && !text.equalsIgnoreCase("")) {
            	
                searchText = text.trim();

                if ((searchphrase != null) && !searchphrase.equalsIgnoreCase("") &&
                        !searchphrase.equalsIgnoreCase("null")) {
                    if (searchphrase.equalsIgnoreCase("any")) {
                        if (searchText.contains(" ")) {
                            String searText = "";
                            String text1 = "";
                            String[] tokens = text.split(" ");
                            String finalStr = "";
                            for (int i = 0; i < tokens.length; i++) {
                            searText = tokens[i];
                            String[] textRemoved = removeText.split("\\#");
                            for (int j = 0; j < textRemoved.length; j++) {
                              if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                            	  tokens[i] = "";
                            	  }
                              }
                            if (tokens[i] != "")
                            	finalStr = finalStr + tokens[i] + " "; 
                            }
                            finalStr=finalStr.trim();
                           // System.out.println("finalStr"+finalStr);
                            if (finalStr.contains(" ")) {                         	
                            	sb.append("(");
                                String[] tokens1 = finalStr.split(" ");
                                for (int k = 0; k < tokens1.length; k++) {
                                	finalStr = tokens1[k];
                                    sb.append("a.introtext like '" + "%" +
                                        finalStr + "%" +
                                        "' or a.title_alias like  '" + "%" +
                                        finalStr + "%" + "' or a.title like  '" +
                                        "%" + finalStr + "%" + "' or a.byline like  '" +
                                        "%" + finalStr + "%" + "'");
                                    if (k != (tokens1.length - 1)) {
                                        sb.append("or ");
                                    }
                                }
                                sb.append(")");
                            	
                            	
                            } else {
                                sb.append("(a.introtext like '" + "%" + finalStr +
                                        "%" + "' or a.title_alias like  '" + "%" +
                                        finalStr + "%" + "' or a.title like  '" + "%" +
                                        finalStr + "%" + "' or a.byline like  '" +
                                        "%" + finalStr + "%" + "')");
                                }
                            } else {
                            sb.append("(a.introtext like '" + "%" + searchText +
                                "%" + "' or a.title_alias like  '" + "%" +
                                searchText + "%" + "' or a.title like  '" + "%" +
                                searchText + "%" + "' or a.byline like  '" +
                                        "%" + searchText + "%" + "')");
                        }
                    } else if (searchphrase.equalsIgnoreCase("all")) {
                        if (searchText.contains(" ")) {
                           
                        	String searText = "";
                            String text1 = "";
                            String[] tokens = text.split(" ");
                            String finalStr = "";
                            for (int i = 0; i < tokens.length; i++) {
                            searText = tokens[i];
                            String[] textRemoved = removeText.split("\\#");
                            for (int j = 0; j < textRemoved.length; j++) {
                              if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                            	  tokens[i] = "";
                            	  }
                              }
                            if (tokens[i] != "")
                            	finalStr = finalStr + tokens[i] + " "; 
                            }
                            finalStr=finalStr.trim();
                        	
                            
                        	
                            if (finalStr.contains(" ")) {                         	
                            	sb.append("(");
                                String[] tokens2 = finalStr.split(" ");
                                for (int l = 0; l < tokens2.length; l++) {
                                	finalStr = tokens2[l];
                                    sb.append("(a.title_alias like  '" + "%" +finalStr + "%" + "' or a.introtext like '" + "%" + finalStr +
                                "%" + "' or a.title like '" + "%" + finalStr +
                                "%" + "' or a.byline like  '" +
                                        "%" + finalStr + "%" + "')");

                                    if (l != (tokens2.length - 1)) {
                                        sb.append("and ");
                                    }
                                }
                                sb.append(")");
                            	
                            	
                            } else {
                                sb.append("(a.introtext like '" + "%" + finalStr +
                                        "%" + "' or a.title_alias like  '" + "%" +
                                        finalStr + "%" + "' or a.title like  '" + "%" +
                                        finalStr + "%" + "' or a.byline like  '" +
                                        "%" + finalStr + "%" + "')");
                                }
                        } else {
                            sb.append("(a.introtext like '" + "%" + searchText +
                                "%" + "' or a.title_alias like  '" + "%" +
                                searchText + "%" + "' or a.title like  '" + "%" +
                                searchText + "%" + "' or a.byline like  '" +
                                        "%" + searchText + "%" + "')");
                        }
                    } else if (searchphrase.equalsIgnoreCase("exact")) {
                        sb.append("(a.introtext like '" + "%" + searchText + "%" +
                            "'  or a.title_alias like  '" + "%" + searchText + "%" +
                            "' or a.title like  '" + "%" + searchText + "%" + "' or a.byline like  '" +
                                        "%" + searchText + "%" + "')");
                    }
                } else {/*
                    if (searchText.contains(" ")) {
                    	
                    	
                    	String searText = "";
                        String text1 = "";
                        String[] tokens = text.split(" ");
                        String finalStr = "";
                        for (int i = 0; i < tokens.length; i++) {
                        searText = tokens[i];
                        String[] textRemoved = removeText.split("\\#");
                        for (int j = 0; j < textRemoved.length; j++) {
                          if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                        	  tokens[i] = "";
                        	  }
                          }
                        if (tokens[i] != "")
                        	finalStr = finalStr + tokens[i] + " "; 
                        }
                        finalStr=finalStr.trim();
                        
                        if (finalStr.contains(" ")) {                         	
                        	sb.append("(");
                            String[] tokens4 = searchText.split(" ");
                            for (int m = 0; m < tokens4.length; m++) {
                                searchText = tokens4[m];
                                sb.append("a.fulltext like '" + "%" + searchText + "%" +
                                    "' or introtext like '" + "%" + searchText + "%" +
                                    "' or title_alias like  '" + "%" + searchText +
                                    "%" + "' or a.byline like  '" +
                                        "%" + searchText + "%" + "' or a.title like  '" +
                                        "%" + searchText + "%" + "'");

                                if (m != (tokens.length - 1)) {
                                    sb.append("or ");
                                }
                            }

                            sb.append(")");
                        	
                        	
                        } else {
                            sb.append("(a.fulltext like '" + "%" + searchText + "%" +
                                    "' or introtext like '" + "%" + searchText + "%" +
                                    "' or title_alias like  '" + "%" + searchText + "%" +
                                    "' or a.byline like  '" +
                                        "%" + searchText + "%" + "' or a.title like  '" +
                                        "%" + searchText + "%" + "')");
                            }
                    } else {
                        sb.append("(a.fulltext like '" + "%" + searchText + "%" +
                            "' or introtext like '" + "%" + searchText + "%" +
                            "' or title_alias like  '" + "%" + searchText + "%" +
                            "' or a.byline like  '" +
                                        "%" + searchText + "%" + "' or a.title like  '" +
                                        "%" + searchText + "%" + "')");
                    }
                */ if (searchText.contains(" ")) {
                   
                	String searText = "";
                    String text1 = "";
                    String[] tokens = text.split(" ");
                    String finalStr = "";
                    for (int i = 0; i < tokens.length; i++) {
                    searText = tokens[i];
                    String[] textRemoved = removeText.split("\\#");
                    for (int j = 0; j < textRemoved.length; j++) {
                      if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                    	  tokens[i] = "";
                    	  }
                      }
                    if (tokens[i] != "")
                    	finalStr = finalStr + tokens[i] + " "; 
                    }
                    finalStr=finalStr.trim();
                	
                   
                	
                    if (finalStr.contains(" ")) {                         	
                    	sb.append("(");
                        String[] tokens2 = finalStr.split(" ");
                        for (int l = 0; l < tokens2.length; l++) {
                        	finalStr = tokens2[l];
                            sb.append("(a.title_alias like  '" + "%" +finalStr + "%" + "' or a.introtext like '" + "%" + finalStr +
                        "%" + "' or a.title like '" + "%" + finalStr +
                        "%" + "' or a.byline like  '" +
                                "%" + finalStr + "%" + "')");

                            if (l != (tokens2.length - 1)) {
                                sb.append("and ");
                            }
                        }
                        sb.append(")");
                    	
                    	
                    } else {
                        sb.append("(a.introtext like '" + "%" + finalStr +
                                "%" + "' or a.title_alias like  '" + "%" +
                                finalStr + "%" + "' or a.title like  '" + "%" +
                                finalStr + "%" + "' or a.byline like  '" +
                                "%" + finalStr + "%" + "')");
                        }
                } else {
                    sb.append("(a.introtext like '" + "%" + searchText +
                        "%" + "' or a.title_alias like  '" + "%" +
                        searchText + "%" + "' or a.title like  '" + "%" +
                        searchText + "%" + "' or a.byline like  '" +
                                "%" + searchText + "%" + "')");
                }
            	
                	}
            }

            if ((search_type != null) && !search_type.equalsIgnoreCase("") &&
                    !search_type.equalsIgnoreCase("null")) {
                if (search_type.equalsIgnoreCase("author")) {
                	sb= new StringBuffer();
                    sb.append("SELECT DISTINCT  a.id        AS articleid,  a.sef_url   AS sef_url,  a.title     AS title,  a.introtext AS introtext,  u.id        AS id,  u.name      AS section ,a.byline    AS byline,a.catid as catid, a.city as city,date_format( a.created,'%M %e, %Y' ) AS publishdate, a.mobile_image as thumbimage, a.kicker_image_alt_text as thumbimagealttext");
                    if ((category != null) && !category.equalsIgnoreCase("null") &&
                            !category.equalsIgnoreCase("")) {
                    	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u ,jos_categories as cat where ");
                    }else{
                    	
                    	  sb.append(" FROM jos_content AS a,jos_article_section as ars ,jos_sections AS u  where ");
                    }
                  sb.append("  a.byline like '" + "%" + text  + "%" + "'");
                }
            }

            // Check for the section
            if ((section != null) && !section.equalsIgnoreCase("") &&
                    !section.equalsIgnoreCase("null")) {
                sb.append(" AND u.id = '" + section + "'");
            }

            // Check for the form date
            if ((fromdate != null) && (todate != null) &&
                    !fromdate.equalsIgnoreCase("null") &&
                    !todate.equalsIgnoreCase("null") &&
                    !fromdate.equalsIgnoreCase("") && !todate.equalsIgnoreCase("")) {
                sb.append(" AND date(a.created) BETWEEN '" + fromdate + "' AND '" +
                    todate + "'");
            } else {
               
            	sb.append("");
            }

            // Check for the Category
            if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
                sb.append(" AND a.catid = '" + category + "'");
            }

            if ((ordering != null) && !ordering.equalsIgnoreCase("null") &&
                    !ordering.equalsIgnoreCase("")) {
                if (ordering.equalsIgnoreCase("oldest")) {
                    ordering = "a.created ASC";
                } else if (ordering.equalsIgnoreCase("alpha")) {
                    ordering = "a.title_alias ASC";
                } else if (ordering.equalsIgnoreCase("newest")) {
                    ordering = "a.created DESC";
                } else {
                    ordering = "a.created DESC";
                }
            } else {
                ordering = "a.created DESC";
            }

            //sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 and u.id=cat.section and a.primary_category!='86'  GROUP BY a.id ORDER BY  " +ordering + "");
            
         
            sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 AND a.primary_category =86 ");
            
           /* if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
                sb.append(" AND a.primary_category  LIKE '" + section + "#" + category + "%'");
            }*/
       
            sb.append(" GROUP BY a.id ORDER BY  " +ordering + "");
            String query = sb.toString();
           // System.out.println("QUESRY: "+query);

            return query;
        }
    public String getPhotoQyery(String text, String search_type,
            String searchphrase, String section, String fromdate, String todate,
            String date_type, String ordering, String category,String removeText) {
    	
    	//System.out.println("getPhotoQyery Executed ");
            String searchText = "";
            StringBuffer sb = null;        
            sb=new StringBuffer();
            sb.append("SELECT  a.id AS articleid, a.sef_url AS sef_url, a.Gallery_name AS title, p.photo_title AS introtext,u.id AS id,u.title AS section ,p.photo_title AS byline,p.id as catid, a.thumb_image as thumb_image,date_format( a.created,'%M %e, %Y' ) AS publishdate,u.sef_url  AS section_sefUrl, a.thumb_image as thumbimage, a.Gallery_name as thumbimagealttext");
            
            if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
            	  sb.append(" FROM jos_gallerynames AS a,jos_photocategories AS u,jos_photogallery p where ");
            }else{
            	
            	  sb.append(" FROM jos_gallerynames AS a,jos_photocategories AS u,jos_photogallery p where ");
            }
          

            if ((text != null) && !text.equalsIgnoreCase("")) {
            	
                searchText = text.trim();

                if ((searchphrase != null) && !searchphrase.equalsIgnoreCase("") &&
                        !searchphrase.equalsIgnoreCase("null")) {
                    if (searchphrase.equalsIgnoreCase("any")) {
                        if (searchText.contains(" ")) {
                            String searText = "";
                            String text1 = "";
                            String[] tokens = text.split(" ");
                            String finalStr = "";
                            for (int i = 0; i < tokens.length; i++) {
                            searText = tokens[i];
                            String[] textRemoved = removeText.split("\\#");
                            for (int j = 0; j < textRemoved.length; j++) {
                              if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                            	  tokens[i] = "";
                            	  }
                              }
                            if (tokens[i] != "")
                            	finalStr = finalStr + tokens[i] + " "; 
                            }
                            finalStr=finalStr.trim();
                            //System.out.println("finalStr"+finalStr);
                            if (finalStr.contains(" ")) {                         	
                            	sb.append("(");
                                String[] tokens1 = finalStr.split(" ");
                                for (int k = 0; k < tokens1.length; k++) {
                                	finalStr = tokens1[k]; 
                                    sb.append("p.photo_title like '" + "%" + finalStr +
                                        "%" + "' or a.Gallery_name like'" + "%" +
                                        finalStr + "%" + "'");
                                    if (k != (tokens1.length - 1)) {
                                        sb.append("or ");
                                    }
                                }
                                sb.append(")");
                            	
                            	
                            } else {
                                sb.append("(p.photo_title like'" + "%" + finalStr +
                                        "%" + "' or a.Gallery_name like  '" + "%" + finalStr +
                                        "%" + "')");
                                }
                            } else {
                            sb.append("(p.photo_title like '" + "%" + searchText +
                                "%" + "' or a.Gallery_name like '" + "%" + searchText +
                                "%" + "')");
                        }
                    } else if (searchphrase.equalsIgnoreCase("all")) {
                        if (searchText.contains(" ")) {
                           
                        	String searText = "";
                            String text1 = "";
                            String[] tokens = text.split(" ");
                            String finalStr = "";
                            for (int i = 0; i < tokens.length; i++) {
                            searText = tokens[i];
                            String[] textRemoved = removeText.split("\\#");
                            for (int j = 0; j < textRemoved.length; j++) {
                              if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                            	  tokens[i] = "";
                            	  }
                              }
                            if (tokens[i] != "")
                            	finalStr = finalStr + tokens[i] + " "; 
                            }
                            finalStr=finalStr.trim();
                        	
                           
                        	
                            if (finalStr.contains(" ")) {                         	
                            	sb.append("(");
                                String[] tokens2 = finalStr.split(" ");
                                for (int l = 0; l < tokens2.length; l++) {
                                	finalStr = tokens2[l];
                                    sb.append("(p.photo_title like '" + "%" + finalStr +"%" + "' or a.Gallery_name like  '" + "%" +finalStr + "%" + "')");

                                    if (l != (tokens2.length - 1)) {
                                        sb.append("and ");
                                    }
                                }
                                sb.append(")");
                            	
                            	
                            } else {
                                 sb.append("(p.photo_title like '" + "%" + finalStr +"%" + "' or a.Gallery_name like '" + "%" +finalStr + "%" + "')");
                                }
                        } else {
                            sb.append("(p.photo_title like  '" + "%" + searchText +
                                "%" + "' or a.Gallery_name like '" + "%" + searchText +
                                "%" + "')");
                        }
                    } else if (searchphrase.equalsIgnoreCase("exact")) {
                        sb.append("(p.photo_title like '" + "%" + searchText + "%" +
                            "'  or a.Gallery_name like'" + "%" + searchText + "%" + "')");
                    }
                } else {/*
                    if (searchText.contains(" ")) {
                    	
                    	
                    	String searText = "";
                        String text1 = "";
                        String[] tokens = text.split(" ");
                        String finalStr = "";
                        for (int i = 0; i < tokens.length; i++) {
                        searText = tokens[i];
                        String[] textRemoved = removeText.split("\\#");
                        for (int j = 0; j < textRemoved.length; j++) {
                          if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                        	  tokens[i] = "";
                        	  }
                          }
                        if (tokens[i] != "")
                        	finalStr = finalStr + tokens[i] + " "; 
                        }
                        finalStr=finalStr.trim();
                        
                        if (finalStr.contains(" ")) {                         	
                        	sb.append("(");
                            String[] tokens4 = searchText.split(" ");
                            for (int m = 0; m < tokens4.length; m++) {
                                searchText = tokens4[m];
                                sb.append("p.photo_title like '" + "%" + searchText + "%" +
                                    "'  or a.Gallery_name like '" + "%" + searchText + "%" + "'");

                                if (m != (tokens.length - 1)) {
                                    sb.append("or ");
                                }
                            }

                            sb.append(")");
                        	
                        	
                        } else {
                            sb.append("(p.photo_title like '" + "%" + searchText + "%" +
                                    "' or a.Gallery_name like '" + "%" + searchText + "%" + "')");
                            }
                    } else {
                        sb.append("(p.photo_title like  '" + "%" + searchText + "%" +
                            "' or a.Gallery_name like'" + "%" + "')");
                    }
                */
                    if (searchText.contains(" ")) {
                        
                    	String searText = "";
                        String text1 = "";
                        String[] tokens = text.split(" ");
                        String finalStr = "";
                        for (int i = 0; i < tokens.length; i++) {
                        searText = tokens[i];
                        String[] textRemoved = removeText.split("\\#");
                        for (int j = 0; j < textRemoved.length; j++) {
                          if (tokens[i].toLowerCase().matches(textRemoved[j])) {
                        	  tokens[i] = "";
                        	  }
                          }
                        if (tokens[i] != "")
                        	finalStr = finalStr + tokens[i] + " "; 
                        }
                        finalStr=finalStr.trim();
                    	
                        
                    	
                        if (finalStr.contains(" ")) {                         	
                        	sb.append("(");
                            String[] tokens2 = finalStr.split(" ");
                            for (int l = 0; l < tokens2.length; l++) {
                            	finalStr = tokens2[l];
                                sb.append("(p.photo_title like '" + "%" + finalStr +"%" + "' or a.Gallery_name like  '" + "%" +finalStr + "%" + "')");

                                if (l != (tokens2.length - 1)) {
                                    sb.append("and ");
                                }
                            }
                            sb.append(")");
                        	
                        	
                        } else {
                             sb.append("(p.photo_title like '" + "%" + finalStr +"%" + "' or a.Gallery_name like '" + "%" +finalStr + "%" + "')");
                            }
                    } else {
                        sb.append("(p.photo_title like  '" + "%" + searchText +
                            "%" + "' or a.Gallery_name like '" + "%" + searchText +
                            "%" + "')");
                    }
                
                	
                }
            }

            if ((search_type != null) && !search_type.equalsIgnoreCase("") &&
                    !search_type.equalsIgnoreCase("null")) {
                if (search_type.equalsIgnoreCase("author")) {
                	sb= new StringBuffer();
                    sb.append("SELECT DISTINCT a.id AS articleid, a.sef_url AS sef_url, a.Gallery_name AS title, p.photo_title AS introtext,u.id AS id,u.title AS section ,p.photo_title AS byline,p.id as catid, a.thumb_image as thumb_image,date_format( a.created,'%M %e, %Y' ) AS publishdate, a.thumb_image as thumbimage, a.Gallery_name as thumbimagealttext  ");
                    if ((category != null) && !category.equalsIgnoreCase("null") &&
                            !category.equalsIgnoreCase("")) {
                    	  sb.append(" FROM jos_gallerynames AS a,jos_photocategories AS u,jos_photogallery p where ");
                    }else{
                    	
                    	  sb.append(" FROM jos_gallerynames AS a,jos_photocategories AS u,jos_photogallery p where ");
                    }
                  sb.append("  p.photo_title '" + "%" + text  + "%" + "'");
                }
            }

            // Check for the section
            if ((section != null) && !section.equalsIgnoreCase("") &&
                    !section.equalsIgnoreCase("null")) {
                sb.append(" AND u.id = '" + section + "'");
            }

            // Check for the form date
            if ((fromdate != null) && (todate != null) &&
                    !fromdate.equalsIgnoreCase("null") &&
                    !todate.equalsIgnoreCase("null") &&
                    !fromdate.equalsIgnoreCase("") && !todate.equalsIgnoreCase("")) {
                sb.append(" AND date(a.created) BETWEEN '" + fromdate + "' AND '" +
                    todate + "'");
            } else {
               
            	sb.append("");
            }

            // Check for the Category
            if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
                sb.append(" AND cat.id = '" + category + "'");
            }

            if ((ordering != null) && !ordering.equalsIgnoreCase("null") &&
                    !ordering.equalsIgnoreCase("")) {
                if (ordering.equalsIgnoreCase("oldest")) {
                    ordering = "a.created ASC";
                } else if (ordering.equalsIgnoreCase("alpha")) {
                    ordering = "a.title_alias ASC";
                } else if (ordering.equalsIgnoreCase("newest")) {
                    ordering = "a.created DESC";
                } else {
                    ordering = "a.created DESC";
                }
            } else {
                ordering = "a.created DESC";
            }

            //sb.append("AND a.blogcategory = 0 AND a.state = 1 and ars.article_id=a.id and u.id = ars.section_id  AND u.published = 1 and u.id=cat.section and a.primary_category!='86'  GROUP BY a.id ORDER BY  " +ordering + "");
            
         
            sb.append("AND a.published = 1 and a.id = p.gallery_id and u.id=a.photo_category GROUP BY a.id ORDER BY a.created DESC");
            
           /* if ((category != null) && !category.equalsIgnoreCase("null") &&
                    !category.equalsIgnoreCase("")) {
                sb.append(" AND a.primary_category  LIKE '" + section + "#" + category + "%'");
            }*/
       
            //sb.append(" GROUP BY a.id ORDER BY  " +ordering + "");
            String query = sb.toString();
            //System.out.println("QUESRY: "+query);

            return query;
        }
}
