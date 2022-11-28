zip:
	gradlew build
	gradlew buildClassDiagram
	latexmk felhasznaloi_kezikonyv.tex
	mkdir -p FK0IEH/app
	cp -r app/src FK0IEH/app
	cp app/build/diagrams/main.svg FK0IEH
	cp build/felhasznaloi_kezikonyv.pdf FK0IEH
	zip FK0IEH.zip -r FK0IEH