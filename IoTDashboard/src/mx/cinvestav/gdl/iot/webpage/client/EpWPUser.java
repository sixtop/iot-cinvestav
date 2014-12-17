package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class EpWPUser extends IoTEntryPoint
{
	private DialogBox dbWait = new DialogBox();

	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddUserDTO = new Button("Add User");
	
	private CellTable<UserDTO> tableUserDTO = new CellTable<UserDTO>();
	private ListDataProvider<UserDTO> dataProvider = new ListDataProvider<UserDTO>();
	private List<UserDTO> list;

	private static final LoginServiceAsync loginService = GWT.create(LoginService.class);
	private List<UserDTO> USERS;
	
	private DialogBox dialogBox = new DialogBox();
	private Label lbDialogBox=new Label();
	private Button btYes = new Button("Yes");
	private Button btNo = new Button("No");
	private VerticalPanel dialogPanel=new VerticalPanel();
	
	
	@Override
	public void continueModuleLoad() 
	{
		//super.onModuleLoad();
		showDialogWait();
		loginService.getUser(null, new AsyncCallback<List<UserDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						dbWait.hide();
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<UserDTO> result)
					{
						dbWait.hide();
						USERS=result;
					
			  		    dataProvider.addDataDisplay(tableUserDTO);
			  		  
			  	        list = dataProvider.getList();
			  		    for (UserDTO c : USERS) {
			  		      list.add(c);
			  		    }

						
					}
				});		
		
		
	   TextColumn<UserDTO> idColumn = new TextColumn<UserDTO>() {
	      @Override
	      public String getValue(UserDTO c) {
	        return c.getId()+"";
	      }
	    };
	    idColumn.setSortable(true);
	    
	   
	    TextColumn<UserDTO> usernameColumn = new TextColumn<UserDTO>() {
	      @Override
	      public String getValue(UserDTO c) {
	        return c.getUsername();
	      }
	    };
	    usernameColumn.setSortable(true);

	    TextColumn<UserDTO> nameColumn = new TextColumn<UserDTO>() {
		      @Override
		      public String getValue(UserDTO c) {
		        return c.getName();
		      }
		    };
		    
		nameColumn.setSortable(true);

	    TextColumn<UserDTO> emailColumn = new TextColumn<UserDTO>() {
		      @Override
		      public String getValue(UserDTO c) {
		        return c.getEmail();
		      }
		    };
	     emailColumn.setSortable(true);
	        
	       ActionCell<UserDTO> editAction = new ActionCell<UserDTO>("Edit", new ActionCell.Delegate<UserDTO>() {
	            public void execute(UserDTO c){
	            	Window.Location.replace("addUser.jsp?idUser="+c.getId());
	            }
	        });

	       IdentityColumn<UserDTO> editColumn = new IdentityColumn<>(editAction);
	        //Column<UserDTO, ActionCell<UserDTO>> editColumn = identityColumn;
	        
	        ActionCell<UserDTO> deleteAction = new ActionCell<UserDTO>("Delete", new ActionCell.Delegate<UserDTO>() {
	            public void execute(UserDTO c){
	            	
	            	dialogBox.setAnimationEnabled(true);
					dialogBox.setGlassEnabled(true);
				    dialogBox.center();

				    dialogBox.setText("Warning");

				    lbDialogBox.setText("Are you sure to delete the User? ");
				    dialogPanel.add(lbDialogBox);
				    dialogPanel.setCellHorizontalAlignment(lbDialogBox, HasHorizontalAlignment.ALIGN_CENTER);

				    HorizontalPanel buttonPanel=new HorizontalPanel();
				    buttonPanel.add(btYes);
				    buttonPanel.add(btNo);
				    
				    dialogPanel.add(buttonPanel);
				    dialogPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);
				    
				    dialogBox.add(dialogPanel);
				    
				    dialogBox.show();
				    
	            }
	        });

	        IdentityColumn<UserDTO> deleteColumn = new IdentityColumn<>(deleteAction);
	
  		     tableUserDTO.addColumn(idColumn, "ID");
  			 tableUserDTO.addColumn(usernameColumn, "User name");
  			 tableUserDTO.addColumn(nameColumn, "Name");
  			 tableUserDTO.addColumn(emailColumn, "Email");
  			 tableUserDTO.addColumn(editColumn,"Edit");
  			 tableUserDTO.addColumn(deleteColumn,"Delete");
  			
  		    ListHandler<UserDTO> sortHandler = new ListHandler<UserDTO>(dataProvider.getList());
  		    tableUserDTO.addColumnSortHandler(sortHandler);
  		
  		  sortHandler.setComparator(idColumn,new Comparator<UserDTO>() {
  			  @Override
  		          public int compare(UserDTO o1, UserDTO o2) {
  		              return o1.getId()-o2.getId();
  		          }
  		        });
	
 		    tableUserDTO.getColumnSortList().push(idColumn);
  		
  		sortHandler.setComparator(usernameColumn,new Comparator<UserDTO>() {
  			@Override
  		          public int compare(UserDTO o1, UserDTO o2) {
  		              return  o1.getUsername().compareTo(o2.getUsername());
  		          }
  		        });

  		sortHandler.setComparator(nameColumn,new Comparator<UserDTO>() {
  			@Override
  		          public int compare(UserDTO o1, UserDTO o2) {
  		              return  o1.getName().compareTo(o2.getName());
  		          }
  		        });

  		
  		sortHandler.setComparator(emailColumn,new Comparator<UserDTO>() {
  			@Override
		          public int compare(UserDTO o1, UserDTO o2) {
		              return o1.getEmail().compareTo(o2.getEmail());
  			}
		        });
		 
				 
		  SimplePager pager = new SimplePager(TextLocation.CENTER, true, true);
		  pager.setDisplay(tableUserDTO);
		  pager.setPageSize(10);  
		    
		    formPanel.add(btAddUserDTO);
		    formPanel.add(tableUserDTO);
		    formPanel.setCellHorizontalAlignment(tableUserDTO, HasHorizontalAlignment.ALIGN_CENTER);
		    formPanel.add(pager);
		    formPanel.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		    
		 
		    
		    RootPanel.get("formContainer").add(formPanel);
		    
	    
		    	btAddUserDTO.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addUser.jsp");
	              }
	            });
		    	
		    	/*btYes.addClickHandler(new ClickHandler() {
		              public void onClick(ClickEvent event) {
		            	  entityService.deleteEntity(new UserDTO(), index, new AsyncCallback<Void>()
						{

							@Override
							public void onFailure(Throwable caught)
							{
								
								
							}

							@Override
							public void onSuccess(Void result)
							{
														
							}
						});
		            	
		            	dialogBox.hide();
		            	Window.Location.reload();
		            	
		              }
		            });*/
		    
		    btNo.addClickHandler(new ClickHandler() {
	            public void onClick(ClickEvent event) {
	              dialogBox.hide();
	            }
	          });
	}
	
public void showDialogWait(){
		
		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

	    VerticalPanel dialogContents = new VerticalPanel();
	    
	    dialogContents.setSpacing(4);
	    
	    Image image = new Image();
	    
	    image.setUrl(GWT.getHostPageBaseURL()+"images/loading2.gif");
	    
	    
	    dialogContents.add(image);
	    dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    dbWait.setWidget(dialogContents);
	    dbWait.show();
		
	}
}