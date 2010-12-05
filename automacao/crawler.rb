# Ricardo Bernardelli - 405.2941-1
# André Daumas - 404.0224-1

require 'open-uri'
require 'nokogiri'

puts Nokogiri::HTML(open(URI.escape("http://en.wikipedia.com")).read).css("div#mp-tfa p a:first").first.attributes["title"].value
