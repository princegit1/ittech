package com.itgd.exception;

public class IndiaTodayHelperException extends RuntimeException {
	
	 private static final long serialVersionUID = 8344868586966792915L;
	    
	   	    
	    public IndiaTodayHelperException() { }
	    
	    public IndiaTodayHelperException(String msg) {
	        super(msg);
	    }
	    
	    public IndiaTodayHelperException(Throwable cause) {
	        super(cause);
	    }
	    
	    public IndiaTodayHelperException(String msg, Throwable cause) {
	        super(msg, cause);
	    }
	    	    
	      
	    String toDefaultString() {
	        return super.toString();
	    }

}
