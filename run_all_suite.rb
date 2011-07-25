["barnesHut","montecarlo"].each do |program|
	toexe = "ant -f ../compare/chord/main/build.xml -Dchord.work.dir="+program+" run > "+program+"/jchord_execution.txt;"
	puts system toexe
end
