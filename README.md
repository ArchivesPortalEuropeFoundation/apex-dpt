# Archives Portal Europe Data Preparation Tool (DPT)

This is the main project for the DPT, a standalone tool, which can be used by content providers to prepare their data locally before upload to the portal.

This tool has to be built before building any other Archives Portal Europe projects.

Licensed under the EUPL (the "Licence"), Version 1.1 or subsequent versions – once they are approved by the European Commission. You may not use this work except in compliance with the Licence. You can find more information about the latest version of the Licence at:

https://joinup.ec.europa.eu/collection/eupl/solution/eupl-freeopen-source-software-licence-european-union

Unless required by applicable law or agreed to in writing, software distributed under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied. See the Licence for the specific language governing permissions and limitations under the Licence.

===============================================================================================

## Table of contents
- DPTstandalone 
- the actual software DPTutils 
- helper classes xsl 
- main conversion scripts license 
- detailed licensing information

To run in IDE: Make sure that the DPTstandalone folder contains either a link to (Linux, MacOS) or a physical copy of (Windows) the xsl folder.

TROUBLESHOOTING:
There is an issue with the code not compiling under Java 8. This is caused by Javadoc generated by an external tool for some of the classes which is not in compliance with doclint. As a temporary workaround, make sure to add -Xdoclint:none to the argument list of your compiler.
