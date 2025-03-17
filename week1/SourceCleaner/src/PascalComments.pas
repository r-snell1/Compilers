program PascalComments

{ 
  Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
  Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. 
}


var
	isOnGod : boolean;
	isActive : boolean;

(* 
  Nulla quis sem at nibh elementum imperdiet. 
  Duis sagittis ipsum. Praesent mauris. 
  Fusce nec tellus sed augue semper porta. 
*)

begin
	// Lorem ipsum dolor sit amet, consectetur adipiscing elit.  
	// Integer nec odio. Praesent libero. Sed cursus ante dapibus diam.  

	// Nulla quis sem at nibh elementum imperdiet.  
	
	// Fusce nec tellus sed augue semper porta.  

	// Morbi in ipsum sit amet pede facilisis laoreet.  
	// Donec lacus nunc, viverra nec, blandit vel, egestas et, augue.  
	// Vestibulum tincidunt malesuada tellus.  
	isActive := false;
	// Duis sagittis ipsum. Praesent mauris.  
	isOnGod := NOT isActive;

	{ 
  	 Morbi in ipsum sit amet pede facilisis laoreet. 
  	 Donec lacus nunc, viverra nec, blandit vel, egestas et, augue. 
  	 Vestibulum tincidunt malesuada tellus. 
	}

	writeln('what''s the GAP status? ', NOT 65 = 55)
end.