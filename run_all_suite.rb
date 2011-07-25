$programs = ["barnesHut","montecarlo","em3d","junit","coref","weka","lusearch"]
$programs_short = ["barnesHut"]
$programs = $programs_short

$programs.each do |program|
	toexe = "ant -f ../compare/chord/main/build.xml -Dchord.work.dir="+program+" run > "+program+"/jchord_execution.txt;"
	puts system toexe
end