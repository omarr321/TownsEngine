; Name of Installer
Name "TownsEngine"

OutFile "TownsEngineInstaller.exe"

; Request application privileges for Windows Vista and higher
RequestExecutionLevel admin

; Build Unicode installer
Unicode True

; The default installation directory
InstallDir $PROGRAMFILES\TownsEngine

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\TOWNSENGINE" "Install_Dir"

!include LogicLib.nsh
!include WinCore.nsh
!include Integration.nsh
;--------------------------------
; Pages

Page components
Page directory
Page instfiles

UninstPage uninstConfirm
UninstPage instfiles

;--------------------------------

Section "TownsEngine (required)"
  SectionIn RO
  

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put the src files there
  File /r "src"
  File "README.txt"
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\TOWNSENGINE "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\TownsEngine" "DisplayName" "TownsEngine"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\TownsEngine" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\TownsEngine" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\TownsEngine" "NoRepair" 1
  WriteUninstaller "$INSTDIR\uninstall.exe"
  
SectionEnd

!define ASSOC_EXT ".town"
!define ASSOC_PROGID "Towns.Engine"
!define ASSOC_VERB "TownsEngine"
!define ASSOC_APPEXE "townsEngineF.bat"
Section "Open *.town with TownsEngine"
	; Register file type
	WriteRegStr ShCtx "Software\Classes\${ASSOC_PROGID}\DefaultIcon" "" "$INSTDIR\src\icon.ico,0"
	WriteRegStr ShCtx "Software\Classes\${ASSOC_PROGID}\shell\${ASSOC_VERB}\command" "" '"$INSTDIR\src\${ASSOC_APPEXE}" "%1"'
	WriteRegStr ShCtx "Software\Classes\${ASSOC_EXT}" "" "${ASSOC_PROGID}"
  
	!ifdef REGISTER_DEFAULTPROGRAMS
	WriteRegStr ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities" "ApplicationDescription" "A simple choose your own adventure sripting language"
	WriteRegStr ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities\FileAssociations" "${ASSOC_EXT}" "${ASSOC_PROGID}"
	WriteRegStr ShCtx "Software\RegisteredApplications" "TownsEngine" "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities"
	!endif
  
	WriteRegStr HKCR "${ASSOC_EXT}" "" "${ASSOC_PROGID}"
	WriteRegStr HKCR "${ASSOC_PROGID}" "" "${ASSOC_PROGID}"
	WriteRegStr HKCR "${ASSOC_VERB}.Settings\DefaultIcon" "" "$INSTDIR\src\icon.ico,0"
	WriteRegStr HKCR "${ASSOC_VERB}.Name\shell" "" "open"
	WriteRegStr HKCR "${ASSOC_VERB}.Name\shell\open\command" "" '"$InstDir\src\${ASSOC_APPEXE}" "%1"'
	WriteRegStr HKCR "${ASSOC_EXT}" "" "${ASSOC_PROGID}"
	WriteRegStr HKCR "${ASSOC_PROGID}" "" "${ASSOC_PROGID}"
	WriteRegStr HKCR "${ASSOC_VERB}.Settings\DefaultIcon" "" "$INSTDIR\src\icon.ico,0"
	WriteRegStr HKCR "${ASSOC_VERB}.Name\shell" "" "open"
	WriteRegStr HKCR "${ASSOC_VERB}.Name\shell\open\command" "" '"$InstDir\src\${ASSOC_APPEXE}" "%1"'

  
  ${NotifyShell_AssocChanged}
SectionEnd

Section "Start Menu Shortcut"
	CreateDirectory "$SMPROGRAMS\TownsEngine"
	CreateShortcut "$SMPROGRAMS\TownsEngine\Uninstall.lnk" "$INSTDIR\uninstall.exe"
	SetOutPath "$INSTDIR\src"
	CreateShortcut "$DESKTOP\TownsEngine.lnk" "$INSTDIR\src\townsEngine.bat" "" "$INSTDIR\src\icon.ico" 0
	SetOutPath $INSTDIR
SectionEnd

SubSection "Desktop Shortcuts"
	Section "TownsEngine"
		SetOutPath "$INSTDIR\src"
		CreateShortcut "$DESKTOP\TownsEngine.lnk" "$INSTDIR\src\townsEngine.bat" "" "$INSTDIR\src\icon.ico" 0
		SetOutPath $INSTDIR
	SectionEnd

	SubSection "Support"
		Section "Wiki"
			CreateDirectory "$DESKTOP\TownsEngine Support"
			CreateShortcut "$DESKTOP\TownsEngine Support\TownsEngineWiki.lnk" "https://omarr321.github.io/TownsEngine/#/" "" "$INSTDIR\src\icon.ico" 0
		SectionEnd

		Section "Examples"
			CreateDirectory "$DESKTOP\TownsEngine Support"
			SetOutPath "$DESKTOP\TownsEngine Support"
			File /r "support\*"
			SetOutPath $INSTDIR
			
		SectionEnd
	SubSectionEnd
SubSectionEnd

;--------------------------------

; Uninstaller

Section "Uninstall"
  
	; Remove registry keys
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\TownsEngine"
	DeleteRegKey HKLM SOFTWARE\TOWNSENGINE
	
	DeleteRegKey HKCR "${ASSOC_EXT}"
	DeleteRegKey HKCR "${ASSOC_PROGID}"
	DeleteRegKey HKCR "${ASSOC_VERB}.Settings\DefaultIcon"
	DeleteRegKey HKCR "${ASSOC_VERB}.Name\shell"
	DeleteRegKey HKCR "${ASSOC_VERB}.Name\shell\open\command"
  
  ; Remove files and uninstaller
  Delete $INSTDIR\test.nsi
  Delete $INSTDIR\uninstall.exe

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\TownsEngine\*.lnk"
  Delete "$DESKTOP\TownsEngine.lnk"

  ; Remove directories
  RMDir /r "$SMPROGRAMS\TownsEngine"
  RMDir /r "$INSTDIR"
  RMDir /r "$DESKTOP\TownsEngine Support"

SectionEnd

Section un.ShellAssoc
  # Unregister file type
  ClearErrors
  DeleteRegKey ShCtx "Software\Classes\${ASSOC_PROGID}\shell\${ASSOC_VERB}"
  DeleteRegKey /IfEmpty ShCtx "Software\Classes\${ASSOC_PROGID}\shell"
  ${IfNot} ${Errors}
    DeleteRegKey ShCtx "Software\Classes\${ASSOC_PROGID}\DefaultIcon"
  ${EndIf}
  ReadRegStr $0 ShCtx "Software\Classes\${ASSOC_EXT}" ""
  DeleteRegKey /IfEmpty ShCtx "Software\Classes\${ASSOC_PROGID}"
  ${IfNot} ${Errors}
  ${AndIf} $0 == "${ASSOC_PROGID}"
    DeleteRegValue ShCtx "Software\Classes\${ASSOC_EXT}" ""
    DeleteRegKey /IfEmpty ShCtx "Software\Classes\${ASSOC_EXT}"
  ${EndIf}

  # Unregister "Default Programs"
  !ifdef REGISTER_DEFAULTPROGRAMS
  DeleteRegValue ShCtx "Software\RegisteredApplications" "Nullsoft Test App"
  DeleteRegKey ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities"
  DeleteRegKey /IfEmpty ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}"
  !endif

  # Attempt to clean up junk left behind by the Windows shell
  DeleteRegValue HKCU "Software\Microsoft\Windows\CurrentVersion\Search\JumplistData" "$InstDir\${ASSOC_APPEXE}"
  DeleteRegValue HKCU "Software\Classes\Local Settings\Software\Microsoft\Windows\Shell\MuiCache" "$InstDir\${ASSOC_APPEXE}.FriendlyAppName"
  DeleteRegValue HKCU "Software\Classes\Local Settings\Software\Microsoft\Windows\Shell\MuiCache" "$InstDir\${ASSOC_APPEXE}.ApplicationCompany"
  DeleteRegValue HKCU "Software\Microsoft\Windows\ShellNoRoam\MUICache" "$InstDir\${ASSOC_APPEXE}" ; WinXP
  DeleteRegValue HKCU "Software\Microsoft\Windows NT\CurrentVersion\AppCompatFlags\Compatibility Assistant\Store" "$InstDir\${ASSOC_APPEXE}"
  DeleteRegValue HKCU "Software\Microsoft\Windows\CurrentVersion\ApplicationAssociationToasts" "${ASSOC_PROGID}_${ASSOC_EXT}"
  DeleteRegValue HKCU "Software\Microsoft\Windows\CurrentVersion\ApplicationAssociationToasts" "Applications\${ASSOC_APPEXE}_${ASSOC_EXT}"
  DeleteRegValue HKCU "Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}\OpenWithProgids" "${ASSOC_PROGID}"
  DeleteRegKey /IfEmpty HKCU "Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}\OpenWithProgids"
  DeleteRegKey /IfEmpty HKCU "Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}\OpenWithList"
  DeleteRegKey /IfEmpty HKCU "Software\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}"
  ;DeleteRegKey HKCU "Software\Microsoft\Windows\Roaming\OpenWith\FileExts\${ASSOC_EXT}"
  ;DeleteRegKey HKCU "Software\Microsoft\Windows\CurrentVersion\Explorer\RecentDocs\${ASSOC_EXT}"
  
  ${NotifyShell_AssocChanged}
SectionEnd