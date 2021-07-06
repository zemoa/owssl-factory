# OWn SSL Factory
## Why
I participate in many projects and I had at least one difficulty that I never found a good and easy solution : accessing a local host through HTTPS. Everytime I had issues with my certificate. Of course, my browser rejects it.
One solution is to create root certificate and register it in the browser and then create other for HTTPS using the root certificate.
I think this is a good solution, but generate and manage those certificates is quite hard.
Here is where OWSSL Factory can help.

## Goals
- Simplify the creation of root certificates
- Simplify the creation of a valid certificate
- List and manage certificates

## How to use it
Todo

## Road map
1. ☑ An app allowing to create root certificate and valid certificate
1. ☑ An that can list and manage created certificate
1. ☑ Help to add the certificate in jre/jdk in order to avoid error when a java method call https endpoint
