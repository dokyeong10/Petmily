package com.ssafy.util;


/**
 * JSP에서 데이터를 page navigation 형식으로 구성하기 위해 사용되는 클래스이다.
 */
public class PageUtility {
    int firstpagecount=0;
    int lastpagecount=0;

    int nextpagecount=0;				//다음 페이지
    int beforepagecount=0;			    //이전 페이지
    int currentpagecount=0;			    //현재 페이지
	
    int beforetenpage=0;				//이전 페이지
    int nexttenpage=0;					//다음 페이지

    int totalrowcount=0;				//총 row 개수
    int totalpagecount=0;				//총 페이지 수
    int displayrowcount=0;				//한 페이지당 보여줄 개수
    int pagePercount = 5;				//페이지 링크 거는 개수      << < 1 2 3 4 5 > >>
    String search;
    /**
     * 현재페이지와 경로 한번에 보여줄 열의 갯수를 세팅하는 생성자
     * @param displayrowcount 한 페이지에 보여줄 게시글 수
     * @param totalrowcount   조회해온 데이터의 전체 row수
     * @param currentpagecount 현재 페이지 
     * @exception Exception
     */
    public PageUtility(int displayrowcount, int totalrowcount, int currentpagecount)
    {
        this.displayrowcount = displayrowcount;
        this.totalrowcount = totalrowcount;
        this.currentpagecount = currentpagecount;
        this.totalpagecount=totalrowcount/displayrowcount;
        if(totalrowcount%displayrowcount!=0){
          this.totalpagecount++;
        }
        
    }
   
    public String getCurrentPageCount()
    {
        return String.valueOf(currentpagecount);
    }
    /**
     * 목록에 출력할 page link를 구성한다.
     * @return 구성한 page 링크를 리턴
     */
    public String getPageBar()    {
  		
        StringBuffer sb=new StringBuffer();
        firstpagecount=((currentpagecount-1)/pagePercount*pagePercount)+1;
        lastpagecount=firstpagecount+pagePercount;

        beforetenpage=firstpagecount-pagePercount;
        beforetenpage= beforetenpage<1?1:beforetenpage;
        nexttenpage=lastpagecount;
        
        System.out.println("firstpagecount:"+firstpagecount);
        System.out.println("lastpagecount:"+lastpagecount);
        System.out.println("beforetenpage:"+beforetenpage);
        System.out.println("nexttenpage:"+nexttenpage);
        
        sb.append("<nav aria-label=\"Page navigation example\">");
        sb.append("<ul class=\"pagination\">");
        
        
        if(beforetenpage<1)     beforetenpage=1;
        if(nexttenpage>totalpagecount)
            nexttenpage=(((totalpagecount-1)/pagePercount)+1)*pagePercount;
        if(lastpagecount>totalpagecount)
            lastpagecount = totalpagecount+1;
		
        if(firstpagecount>pagePercount)
        	sb.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:pagelist("+beforetenpage+")'>First</a></li>");
        else
            sb.append(" <li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\" tabindex=\"-1\">First</a></li>");

        if(((currentpagecount-1)/pagePercount*pagePercount)+1>beforetenpage)
        	sb.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:pagelist("+beforetenpage+")'>Previous</a></li>");
        else
        	sb.append(" <li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\" tabindex=\"-1\">Previous</a></li>");

        for (int i = firstpagecount; i < lastpagecount; i++)
        {
            if(i<=totalpagecount)
            {
                if (i == currentpagecount) {
                	sb.append("<li class=\"page-item active\">");
                	sb.append("<a class=\"page-link\" href='javascript:pagelist("+i+")'>"+ i +"<span class=\"sr-only\">(current)</span></a>");
                	sb.append("</li>");
                	
                }
                else
                	sb.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:pagelist("+i+")'>"+ i + "</a></li>");
            }
        }

        if(nexttenpage<((totalpagecount-1)/pagePercount+1)*pagePercount)
        	sb.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:pagelist("+ (nexttenpage)+ ")'>Next</a></li>");
        else {
            sb.append("<li class=\"page-item disabled\">");
            sb.append("<span class=\"page-link\">Next</span>");
            sb.append("</li>");
        }

        if((((currentpagecount-1)/pagePercount)+1)*pagePercount < nexttenpage)
        	sb.append("<li class=\"page-item\"><a class=\"page-link\" href='javascript:pagelist("+ (nexttenpage)+ ")'>End</a></li>");
        else {
            sb.append("<li class=\"page-item disabled\">");
            sb.append("<span class=\"page-link\">End</span>");
            sb.append("</li>");
        }
        
        sb.append("</ul>");
        sb.append("</nav>");
        		
        return sb.toString();
    }
    public String getTotalPageCount() {       return String.valueOf(totalpagecount);
    }
}