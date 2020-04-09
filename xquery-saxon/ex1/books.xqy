for $x in doc("ex1/books.xml")/books/book
where $x/price>30
return $x/title