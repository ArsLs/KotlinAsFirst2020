������� �� �������
git remote add upstream-my https://github.com/ArsLs/KotlinAsFirst2021.git
git fetch upstream-my
git checkout -b -backport
git cherry-pick git cherry-pick d535f3592006b8f2593c9f881d72c05164aadc13...FETCH_HEAD
git remote add upstream-theirs https://github.com/sql3/KotlinAsFirst2021.git
git fetch upstream-theirs 
git checkout master
git merge backport upstream-theirs/master
��������� �������� � IntelliJ IDEa
git add -A
git commit -m "Sup"
git push
��������� git remote -v ������� � ���� remotes
������� ���� ������������������ ��������
������� � ����