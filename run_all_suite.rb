$programs = ["barnesHut","montecarlo.threads","em3d","junit","coref","weka","lusearch"]

$programs = [ARGV[0]] if !ARGV[0].nil?

puts "Evaluating: "+($programs * " ")

$programs.each do |p|
    postfix_file = File.new("chord.properties.postfix", "r")
    prefix_file = File.new(p+"/chord.properties.prefix", "r")
    prop_file = File.new(p+"/chord.properties","w")
    prefix_file.each {|line| prop_file << line}
    prop_file << "\n"
    postfix_file.each {|line| prop_file << line}
    prop_file.close
end

$programs.each do |p|
    toexe = "ant -f ../compare/chord/main/build.xml -Dchord.work.dir="+p+" run > "+p+"/jchord_execution.txt;"
    worked = system toexe
    if worked
        puts p+" SUCCESS "
        filter_results_exe = "ruby filter_results.rb "+p+" > "+p+"/jchord_results.txt;"
        system filter_results_exe
        puts p+" filtered results"
    else
        puts p+" FAIL"
    end
end
