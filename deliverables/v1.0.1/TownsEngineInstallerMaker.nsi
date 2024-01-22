;!-!GEN INFO!-!
!define NAME "Towns Engine"
!define INSTALLNAME "TownsEngine"
!define VERSION "v1.0.1"
!define SCRIPTDIR "F:\IdeaProjects\TownsEngine\deliverables\v1.0.1"
;!-!GEN INFO!-!



; Name of Installer
Name "${NAME}"

OutFile "${NAME}Installer_${VERSION}.exe"

; Request application privileges for Windows Vista and higher
RequestExecutionLevel admin

; Build Unicode installer
Unicode True

; The default installation directory
InstallDir $PROGRAMFILES\${INSTALLNAME}

; Registry key to check for directory (so if you install again, it will 
; overwrite the old one automatically)
InstallDirRegKey HKLM "Software\${INSTALLNAME}" "Install_Dir"

!include LogicLib.nsh
!include WinCore.nsh
!include Integration.nsh

;--------------------------------
; Modern UI 2 stuff

; Addes Modern UI 2
!include MUI2.nsh

;--------------------------------
;Interface Configuration

!define MUI_ICON "${SCRIPTDIR}\src\icon.ico"
!define MUI_UNICON "${SCRIPTDIR}\src\icon.ico"

;--------------------------------

; Setting some basic stuff
!define MUI_COMPONENTSPAGE_SMALLDESC ;No value
!define MUI_INSTFILESPAGE_COLORS "339933 000000" ;Two colors
!define MUI_FINISHPAGE_NOAUTOCLOSE
!define MUI_WELCOMEPAGE_TITLE "${NAME} ${VERSION}"
!define MUI_WELCOMEPAGE_TEXT "TownsEngine is a simple scripting language that you can use to create branching stories easily. This engine is only used for text-based stories as there is no graphical elements available. It is designed to be simple to use and easy to understand."

; defines pages parms
; Lincense page
!define MUI_LICENSEPAGE_CHECKBOX
!define MUI_LICENSEPAGE_CHECKBOX_TEXT "I Accept"

; Finish page
!define MUI_FINISHPAGE_SHOWREADME https://omarr321.github.io/TownsEngine/#/
!define MUI_FINISHPAGE_SHOWREADME_TEXT "Open Wiki"
!define MUI_FINISHPAGE_NOREBOOTSUPPORT

; Inserting Pages
!insertmacro MUI_PAGE_WELCOME
!insertmacro MUI_PAGE_LICENSE "${SCRIPTDIR}\LICENSE.txt"
!insertmacro MUI_PAGE_COMPONENTS
!insertmacro MUI_PAGE_DIRECTORY
!insertmacro MUI_PAGE_INSTFILES
!insertmacro MUI_PAGE_FINISH

!insertmacro MUI_UNPAGE_WELCOME
!insertmacro MUI_UNPAGE_CONFIRM
!insertmacro MUI_UNPAGE_INSTFILES
!insertmacro MUI_UNPAGE_FINISH

; Languages
!insertmacro MUI_LANGUAGE "English"

Section "${NAME} (required)" Engine
  SectionIn RO
  

  ; Set output path to the installation directory.
  SetOutPath $INSTDIR
  
  ; Put the src files there
  File /r "src"
  File "README.txt"
  
  ; Write the installation path into the registry
  WriteRegStr HKLM SOFTWARE\${INSTALLNAME} "Install_Dir" "$INSTDIR"
  
  ; Write the uninstall keys for Windows
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}" "DisplayName" "${NAME}"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}" "DisplayIcon" "$INSTDIR\src\icon.ico,0"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}" "UninstallString" '"$INSTDIR\uninstall.exe"'
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}" "NoRepair" 1
  WriteUninstaller "$INSTDIR\uninstall.exe"
  
SectionEnd

!define ASSOC_EXT_ND "town"
!define ASSOC_EXT ".town"
!define ASSOC_PROGID "Towns.Engine"
!define ASSOC_VERB "${INSTALLNAME}"
!define ASSOC_APPEXE "townsEngineF.bat"
Section "Open *.town with ${NAME}" Linking
	DeleteRegKey HKCU "SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}"
	DeleteRegKey HKCR "${ASSOC_EXT_ND}_auto_file"

	; Register file type
	WriteRegStr ShCtx "Software\Classes\${ASSOC_PROGID}\DefaultIcon" "" "$INSTDIR\src\icon.ico,0"
	WriteRegStr ShCtx "Software\Classes\${ASSOC_PROGID}\shell\${ASSOC_VERB}\command" "" '"$INSTDIR\src\${ASSOC_APPEXE}" "%1"'
	WriteRegStr ShCtx "Software\Classes\${ASSOC_EXT}" "" "${ASSOC_PROGID}"
  
	WriteRegStr ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities" "ApplicationDescription" "A simple choose your own adventure sripting language"
	WriteRegStr ShCtx "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities\FileAssociations" "${ASSOC_EXT}" "${ASSOC_PROGID}"
	WriteRegStr ShCtx "Software\RegisteredApplications" "${ASSOC_VERB}" "Software\Classes\Applications\${ASSOC_APPEXE}\Capabilities"
  
	WriteRegStr HKCR "${ASSOC_PROGID}" "" "${ASSOC_VERB}"
	WriteRegStr HKCR "${ASSOC_PROGID}\DefaultIcon" "" "$INSTDIR\src\icon.ico,0"
	WriteRegStr HKCR "${ASSOC_PROGID}\shell\open\command" "" '"$INSTDIR\src\${ASSOC_APPEXE}" "%1"'

	WriteRegStr HKCR "${ASSOC_EXT}" "" "${ASSOC_PROGID}"
	
	${NotifyShell_AssocChanged}
SectionEnd

SectionGroup "Shortcuts" GenShort
	Section "Start Menu" ShortMenu
		CreateDirectory "$SMPROGRAMS\${INSTALLNAME}"
		CreateShortcut "$SMPROGRAMS\${INSTALLNAME}\Uninstall.lnk" "$INSTDIR\uninstall.exe"
		SetOutPath "$INSTDIR\src"
		CreateShortcut "$DESKTOP\${INSTALLNAME}.lnk" "$INSTDIR\src\townsEngine.bat" "" "$INSTDIR\src\icon.ico" 0
		SetOutPath $INSTDIR
	SectionEnd
	Section "Desktop" ShortDesktop
		SetOutPath "$INSTDIR\src"
		CreateShortcut "$DESKTOP\${INSTALLNAME}.lnk" "$INSTDIR\src\townsEngine.bat" "" "$INSTDIR\src\icon.ico" 0
		SetOutPath $INSTDIR
	SectionEnd
SectionGroupEnd
SectionGroup "Support" GenSupp
	Section /o "Wiki" SuppWiki
		CreateDirectory "$DESKTOP\${INSTALLNAME} Support"
		CreateShortcut "$DESKTOP\${INSTALLNAME} Support\${INSTALLNAME}Wiki.lnk" "https://omarr321.github.io/TownsEngine/#/" "" "$INSTDIR\src\icon.ico" 0
	SectionEnd

	Section /o "Examples" SuppEx
		CreateDirectory "$DESKTOP\${INSTALLNAME} Support"
		SetOutPath "$DESKTOP\${INSTALLNAME} Support"
		File /r "support\*"
		SetOutPath $INSTDIR	
	SectionEnd
SectionGroupEnd
;--------------------------------
; Decriptions
; LangString DESC_ ${LANG_ENGLISH} ""
LangString DESC_Engine  ${LANG_ENGLISH} "The complier for ${NAME}"
LangString DESC_Linking ${LANG_ENGLISH} "Links the .town ext to be auto opened by the ${NAME} ${VERSION}"
LangString DESC_ShortMenu ${LANG_ENGLISH} "Adds a shortcut to the Start Menu"
LangString DESC_ShortDesktop ${LANG_ENGLISH} "Adds a shortcut to your Desktop"
LangString DESC_SuppWiki ${LANG_ENGLISH} "Adds a link to the wiki to the Support folder on your desktop"
LangString DESC_SuppEx ${LANG_ENGLISH} "Adds 3 example storys to the Support folder on your desktop"
LangString DESC_GenShort ${LANG_ENGLISH} "Shortcuts that can be added to your computer"
LangString DESC_GenSupp ${LANG_ENGLISH} "Support documentation options"
;Assign language strings to sections
  !insertmacro MUI_FUNCTION_DESCRIPTION_BEGIN
    !insertmacro MUI_DESCRIPTION_TEXT ${Engine} $(DESC_Engine)
	!insertmacro MUI_DESCRIPTION_TEXT ${Linking} $(DESC_Linking)
	!insertmacro MUI_DESCRIPTION_TEXT ${ShortMenu} $(DESC_ShortMenu)
	!insertmacro MUI_DESCRIPTION_TEXT ${ShortDesktop} $(DESC_ShortDesktop)
	!insertmacro MUI_DESCRIPTION_TEXT ${SuppWiki} $(DESC_SuppWiki)
	!insertmacro MUI_DESCRIPTION_TEXT ${SuppEx} $(DESC_SuppEx)
	!insertmacro MUI_DESCRIPTION_TEXT ${GenShort} $(DESC_GenShort)
	!insertmacro MUI_DESCRIPTION_TEXT ${GenSupp} $(DESC_GenSupp)
  !insertmacro MUI_FUNCTION_DESCRIPTION_END
;--------------------------------

; Uninstaller

Section "Uninstall"
  
	; Remove registry keys
	DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\${INSTALLNAME}"
	DeleteRegKey HKLM SOFTWARE\TOWNSENGINE
	
	DeleteRegKey HKCR "${ASSOC_EXT}"
	DeleteRegKey HKCR "${ASSOC_PROGID}"

	DeleteRegKey HKCU "SOFTWARE\Microsoft\Windows\CurrentVersion\Explorer\FileExts\${ASSOC_EXT}"
	DeleteRegKey HKCR "${ASSOC_EXT_ND}_auto_file"
  
  ; Remove files and uninstaller
  Delete $INSTDIR\test.nsi
  Delete $INSTDIR\uninstall.exe

  ; Remove shortcuts, if any
  Delete "$SMPROGRAMS\${INSTALLNAME}\*.lnk"
  Delete "$DESKTOP\${INSTALLNAME}.lnk"

  ; Remove directories
  RMDir /r "$SMPROGRAMS\${INSTALLNAME}"
  RMDir /r "$INSTDIR"
  RMDir /r "$DESKTOP\${INSTALLNAME} Support"

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
  DeleteRegValue ShCtx "Software\RegisteredApplications" "${ASSOC_VERB}"
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
  
  ${NotifyShell_AssocChanged}
SectionEnd