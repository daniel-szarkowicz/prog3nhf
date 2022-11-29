zip:
	gradlew build
	gradlew buildClassDiagram
	latexmk felhasznaloi_kezikonyv.tex
	mkdir -p FK0IEH/app
	cp app/*.map FK0IEH/app
	cp -r app/src FK0IEH/app
	cp app/build/diagrams/main.svg FK0IEH/osztalydiagram.svg
	cp build/felhasznaloi_kezikonyv.pdf FK0IEH
	cp settings.gradle FK0IEH
	cp app/build.gradle FK0IEH
	cp -r gradle FK0IEH
	cp gradlew FK0IEH
	cp gradlew.bat FK0IEH
	zip FK0IEH.zip -r -9 FK0IEH

clean:
	rm -rf FK0IEH.zip
	rm -rf FK0IEH