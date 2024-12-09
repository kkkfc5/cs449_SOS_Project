package production.mainapp;

import production.GameEvents.*;
import production.GameLogic.*;
import production.GUI.*;

/*import production.GUI.Gameboard;
import production.GUI.SOS_Window;
import production.GameEvents.EventManager;
import production.GameLogic.SOS_Game_Main;
import production.GameLogic.SOS_Game_Simple;
import production.GameLogic.SOS_Player_Computer;
import production.GameLogic.SOS_Player_Human;
*/

public class SOS_Main {
	
	
	GameStateManager _GameStateManager = new GameStateManager();
	SOS_Window _SOS_Window = new SOS_Window();
	Gameboard _Gameboard = new Gameboard();
	SOS_Game_Simple _SOS_Game_Simple = new SOS_Game_Simple();
	SOS_Game_General _SOS_Game_General = new SOS_Game_General();
	SOS_Player_Human _SOS_Player_Human = new SOS_Player_Human();
	SOS_Player_Computer _SOS_Player_Computer = new SOS_Player_Computer();
	FileOperations _FileOperations = new FileOperations();
	
	
	public SOS_Main() {
		// event manager objects
		var _SOS_Created_Event_Manager = new SOS_Created_Event_Manager();
		var _Update_Utility_Event_Manager = new Update_Utility_Event_Manager();
		var _Game_Over_Event_Manager = new Game_Over_Event_Manager();
		var _Toggle_Turns_Event_Manager = new Toggle_Turns_Event_Manager();
		var _New_Game_Event_Manager = new New_Game_Event_Manager();
		var _Initiate_Game_Event_Manager = new Initiate_Game_Event_Manager();
		var _CellToMouse_Listener_Event_Manager = new CellToMouse_Listener_Event_Manager();
		var _Get_Selected_Cell_Event_Manager = new Get_Selected_Cell_Event_Manager();
		var _Player_Clicked_Event_Manager = new Player_Clicked_Event_Manager();
		var _Draw_Cell_Event_Manager = new Draw_Cell_Event_Manager();
		var _Get_Selected_SO_Event_Manager = new Get_Selected_SO_Event_Manager();
		var _Undo_Event_Manager = new Undo_Event_Manager();
		var _Redo_Event_Manager = new Redo_Event_Manager();
		var _Toggle_Display_Utils_Event_Manager = new Toggle_Display_Utils_Event_Manager();
		
		// main objects
		_GameStateManager = new GameStateManager();
		_SOS_Window = new SOS_Window();
		_Gameboard = new Gameboard();
		_SOS_Game_Simple = new SOS_Game_Simple();
		_SOS_Game_General = new SOS_Game_General();
		_SOS_Player_Human = new SOS_Player_Human();
		_SOS_Player_Computer = new SOS_Player_Computer();
		_FileOperations = new FileOperations();
		
		// CONNECTING THE TEST REFERENCED
		//refGameStateManager(_GameStateManager);
		
		//_Gameboard.TESThuman(_SOS_Player_Human);
		
		
		// sos_window should parent gameboard
		_SOS_Window.connectGameboard(_Gameboard);
		_GameStateManager.connectGameboard(_Gameboard);
		
		
		// connecting game state
		_SOS_Window.connectGameStateManager(_GameStateManager);
		// -- 
		_Gameboard.connectGameStateManager(_GameStateManager);
		// --
		_SOS_Game_Simple.connectGameStateManager(_GameStateManager);
		_SOS_Game_General.connectGameStateManager(_GameStateManager);
		// --
		_Get_Selected_Cell_Event_Manager.connectGameStateManager(_GameStateManager);
		// --
		_SOS_Player_Human.connectGameStateManager(_GameStateManager);
		_SOS_Player_Computer.connectGameStateManager(_GameStateManager);
		
		
		
		_Initiate_Game_Event_Manager.addSimpleGame(_SOS_Game_Simple);
		_Initiate_Game_Event_Manager.addGeneralGame(_SOS_Game_General);
		// --
		_Get_Selected_Cell_Event_Manager.connectPlayerComputer(_SOS_Player_Computer);
		_Get_Selected_Cell_Event_Manager.connectPlayerHuman(_SOS_Player_Human);
		
		
		
		// == connecting events ==
		// -- connecting managers
		_SOS_Window.connectNewGameEventManager(_New_Game_Event_Manager);
		_SOS_Window.connectInitiateGameEventManager(_Initiate_Game_Event_Manager);
		_SOS_Window.connectUndoEventManager(_Undo_Event_Manager);
		_SOS_Window.connectRedoEventManager(_Redo_Event_Manager);
		_SOS_Window.connectToggleDisplayUtilsEventManager(_Toggle_Display_Utils_Event_Manager);
		// --
		_SOS_Game_Simple.connectSOSCreatedEventManager(_SOS_Created_Event_Manager);
		_SOS_Game_Simple.connectToggleTurnsEventManager(_Toggle_Turns_Event_Manager);
		_SOS_Game_Simple.connectUpdateUtilityEventManager(_Update_Utility_Event_Manager);
		_SOS_Game_Simple.connectGetSelectedCellEventManager(_Get_Selected_Cell_Event_Manager);
		_SOS_Game_Simple.connectDrawCellEventManager(_Draw_Cell_Event_Manager);
		_SOS_Game_Simple.connectGameOverEventManager(_Game_Over_Event_Manager);
		// -- 
		_SOS_Game_General.connectSOSCreatedEventManager(_SOS_Created_Event_Manager);
		_SOS_Game_General.connectToggleTurnsEventManager(_Toggle_Turns_Event_Manager);
		_SOS_Game_General.connectUpdateUtilityEventManager(_Update_Utility_Event_Manager);
		_SOS_Game_General.connectGetSelectedCellEventManager(_Get_Selected_Cell_Event_Manager);
		_SOS_Game_General.connectDrawCellEventManager(_Draw_Cell_Event_Manager);
		_SOS_Game_General.connectGameOverEventManager(_Game_Over_Event_Manager);
		// --
		_SOS_Player_Human.connectCellToMouseListenerEventManager(_CellToMouse_Listener_Event_Manager);
		_SOS_Player_Human.connectPlayerClickEventManager(_Player_Clicked_Event_Manager);
		_SOS_Player_Human.connectGetSelectedSOEventManager(_Get_Selected_SO_Event_Manager);
		// --
		_GameStateManager.connectToggleTurnsEventManager(_Toggle_Turns_Event_Manager);
		_GameStateManager.connectFileOperations(_FileOperations);
		
		
		// -- connecting listeners
		_SOS_Created_Event_Manager.addListener(_Gameboard);
		// --
		_Toggle_Turns_Event_Manager.addListener(_SOS_Window);
		_Toggle_Turns_Event_Manager.addListener(_SOS_Game_Simple);
		_Toggle_Turns_Event_Manager.addListener(_SOS_Game_General);
		// --
		_Update_Utility_Event_Manager.addListener(_Gameboard);
		// --
		_Game_Over_Event_Manager.addListener(_SOS_Window);
		_Game_Over_Event_Manager.addListener(_Gameboard);
		_Game_Over_Event_Manager.addListener(_SOS_Player_Human);
		// -- 
		_New_Game_Event_Manager.addListener(_SOS_Game_Simple);
		_New_Game_Event_Manager.addListener(_SOS_Game_General);
		_New_Game_Event_Manager.addListener(_SOS_Player_Human);
		// --
		_CellToMouse_Listener_Event_Manager.addListener(_Gameboard);
		// -- 
		_Player_Clicked_Event_Manager.addListener(_SOS_Game_Simple);
		_Player_Clicked_Event_Manager.addListener(_SOS_Game_General);
		// -- 
		_Get_Selected_SO_Event_Manager.addListener(_SOS_Window);
		// --
		_Initiate_Game_Event_Manager.addListener(_GameStateManager);
		// -- 
		_Draw_Cell_Event_Manager.addListener(_Gameboard);
		// --
		_Undo_Event_Manager.addListener(_GameStateManager);
		_Redo_Event_Manager.addListener(_GameStateManager);
		// -- 
		_Toggle_Display_Utils_Event_Manager.addListener(_GameStateManager);
		_Toggle_Display_Utils_Event_Manager.addListener(_SOS_Game_Simple);
		_Toggle_Display_Utils_Event_Manager.addListener(_SOS_Game_General);
		
		
		
		// GAME START STATE
		_GameStateManager.onInitiateGame(_SOS_Game_Simple);
		_CellToMouse_Listener_Event_Manager.invokeConnect(_SOS_Player_Human);
		_SOS_Window.setVisible(false);
		_SOS_Window.setVisible(true);
	}
	
	
	
	// GETTERS USED FOR THE TEST FUNCTIONS
		public SOS_Window getWindow() {
			return _SOS_Window;
		}
		public GameStateManager getGameStateManager() {
			return _GameStateManager;
		}
		public SOS_Player_Human getPlayerHuman() {
			return _SOS_Player_Human;
		}
}
