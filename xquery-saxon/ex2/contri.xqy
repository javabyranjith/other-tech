xquery version "1.0";
declare option exist:serialize "method=text media-type=text/plain";

let $document := '/ex2/contributors.xml'
(: special characters such as left and right curly brace and newline  :)
let $lcb := '{', $rcb := '}', $nl := '
'
(: json file header and footer as well as item header and footers :)
let $json-header := concat($lcb, $nl, ' "items" : [ ')
let $json-footer := concat($nl, '  ]', $nl,$rcb)
let $item-header := concat($nl, '    ', $lcb, '  ')
let $item-footer := concat('    ', $rcb)
return
<results>{$json-header}
{
   string-join(
      for $contributor in doc($document)/contributors/contributor
      return
<item>{$item-header}label:       "{$contributor/author-name/text()}",
       location:     "{$contributor/location/text()}",
       "image-url":    "{$contributor/image-url/text()}"
{$item-footer}</item>
   , ', ')
}{$json-footer}</results>