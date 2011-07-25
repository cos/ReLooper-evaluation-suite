require 'rubygems'
require 'nokogiri'
require 'open-uri'

# Get a Nokogiri::HTML:Document for the page we’re interested in...

doc = Nokogiri::HTML(open(ARGV[0]+"/chord_output/dataraces_by_obj.html"))

# Do funky things with it using Nokogiri::XML::Node methods...

main_count = 0;
thread_count = 0;
all_count = 0;

races_html = doc.xpath('/html/body/table/tr');
races = races_html.collect do |race_html|
	race = {}
	race[:id] = race_html.xpath("td[1]/a[1]").inner_html;
 	race[:t1] = race_html.xpath("td[2]/a[1]").inner_html;
 	race[:t2] = race_html.xpath("td[4]/a[1]").inner_html;
	race
end

races = races.find_all {|race| race[:id] =~ /\d\.\d/ }

races_on_main = races.find_all { |race| race[:t1] =~ /main/ || race[:t2] =~ /main/ }
races_parallel = races.find_all { |race| !(race[:t1] =~ /main/ || race[:t2] =~ /main/) }

puts "Races involving main:   " + races_on_main.count.to_s
puts "Races parallel section: " + races_parallel.count.to_s
puts "Total number of races:  " + (races_on_main.count + races_parallel.count).to_s

analyzed_fraction = 0.05
analyzed_races_no = analyzed_fraction * races_parallel.count
puts "-"
races_analyzed = (1..analyzed_races_no).collect do |i|
	random_race_id = rand(races_parallel.count) 
	races_parallel[random_race_id]
end

races_analyzed.each do |race|
	puts race[:id].to_s
end

#html body table.summary tbody tr td a




####
# Search for nodes by css
#doc.css('h3.r a.l').each do |link|
#  puts link.content
#end

####
# Search for nodes by xpath
#doc.xpath('//h3/a[@class="l"]').each do |link|
#  puts link.content
#end

####
# Or mix and match.
#doc.search('h3.r a.l', '//h3/a[@class="l"]').each do |link|
#  puts link.content
#end